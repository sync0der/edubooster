package uz.tsue.ricoin.service.impls;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.tsue.ricoin.entity.User;
import uz.tsue.ricoin.repository.UserRepository;
import uz.tsue.ricoin.dto.response.UserResponseDto;
import uz.tsue.ricoin.service.interfaces.UserService;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow(() -> new NoSuchElementException("User not found"));
    }

    @Override
    public User findById(Long id) {
        return userRepository.findById(id).orElseThrow(NoSuchElementException::new);
    }

    @Override
    public List<UserResponseDto> findAll() {
        List<User> userList = userRepository.findAll();
        List<UserResponseDto> users = new ArrayList<>();
        for (User user : userList) {
            users.add(
                    UserResponseDto.builder()
                            .email(user.getEmail())
                            .phoneNumber(user.getPhoneNumber())
                            .firstName(user.getFirstName())
                            .lastName(user.getLastName())
                            .groupName(user.getGroupName())
                            .balance(user.getBalance())
                            .createdDate(user.getCreatedDate())
                            .lastModifiedDate(user.getLastModifiedDate())
                            .orders(user.getOrders())
                            .build()
            );
        }
        return users;
    }

    @Override
    public void save(User user) {
        userRepository.save(user);
    }

    @Override
    public boolean hasEnoughBalance(int productPrice, User user) {
        return user.getBalance() >= productPrice;
    }

    @Override
    public void debitBalance(User user, int amount) {
        user.setBalance(user.getBalance() - amount);
    }

    @Override
    public void addBalance(User user, int amount) {
        user.setBalance(user.getBalance() + amount);
        save(user);
    }

    @Override
    public UserResponseDto getCurrentUser(User user) {
        return UserResponseDto.builder()
                .email(user.getEmail())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .groupName(user.getGroupName())
                .phoneNumber(user.getPhoneNumber())
                .balance(user.getBalance())
                .createdDate(user.getCreatedDate())
                .lastModifiedDate(user.getLastModifiedDate())
                .orders(user.getOrders())
                .build();
    }
}
