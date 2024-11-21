package uz.tsue.ricoin.service.impls;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.tsue.ricoin.dto.request.UserUpdateRequestDto;
import uz.tsue.ricoin.dto.response.UserDto;
import uz.tsue.ricoin.entity.User;
import uz.tsue.ricoin.mapper.UserMapper;
import uz.tsue.ricoin.repository.UserRepository;
import uz.tsue.ricoin.service.interfaces.UserService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow(() -> new NoSuchElementException("User not found"));
    }

    @Override
    public User findById(Long id) {
        return userRepository.findById(id).orElseThrow(NoSuchElementException::new);
    }

    @Override
    public List<UserDto> findAll() {
        List<User> userList = userRepository.findAll();
        List<UserDto> users = new ArrayList<>();
        for (User user : userList) {
            users.add(userMapper.toDto(user));
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
    public UserDto getCurrentUser(User user) {
        return userMapper.toDto(user);
    }


    @Override
    public UserDto update(Long id, UserUpdateRequestDto userDto) {
        User user = findById(id);
        Optional.ofNullable(userDto.getFirstName()).ifPresent(user::setFirstName);
        Optional.ofNullable(userDto.getLastName()).ifPresent(user::setLastName);
        Optional.ofNullable(userDto.getGroupName()).ifPresent(user::setGroupName);
        Optional.ofNullable(userDto.getPhoneNumber()).ifPresent(user::setPhoneNumber);
        user.setLastModifiedDate(LocalDateTime.now());
        userRepository.save(user);
        return userMapper.toDto(user);
    }

    @Override
    public void delete(Long id, HttpServletRequest request) {
        userRepository.deleteById(id);
    }

}

