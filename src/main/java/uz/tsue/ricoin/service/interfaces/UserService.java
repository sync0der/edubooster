package uz.tsue.ricoin.service.interfaces;

import uz.tsue.ricoin.entity.User;
import uz.tsue.ricoin.dto.response.UserResponseDto;

import java.util.List;

public interface UserService {
    User findByEmail(String email);

    User findById(Long id);

    List<UserResponseDto> findAll();

    void save(User user);

    boolean hasEnoughBalance(int amount, User user);

    void debitBalance(User user, int amount);

    void addBalance(User user, int amount);

    UserResponseDto getCurrentUser(User user);


}
