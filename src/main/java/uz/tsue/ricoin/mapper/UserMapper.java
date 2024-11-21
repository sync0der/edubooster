package uz.tsue.ricoin.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import uz.tsue.ricoin.dto.response.OrderDto;
import uz.tsue.ricoin.dto.response.UserDto;
import uz.tsue.ricoin.entity.Order;
import uz.tsue.ricoin.entity.User;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class UserMapper implements CustomMapperInterface<User, UserDto> {
    private final OrderMapper orderMapper;

    @Override
    public UserDto toDto(User user) {

        List<OrderDto> userOrders = new ArrayList<>();
        for (Order order : user.getOrders()) {
            userOrders.add(orderMapper.toDto(order));
        }

        return UserDto.builder()
                .id(user.getId())
                .email(user.getEmail())
                .phoneNumber(user.getPhoneNumber())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .groupName(user.getGroupName())
                .balance(user.getBalance())
                .createdDate(user.getCreatedDate())
                .lastModifiedDate(user.getLastModifiedDate())
                .orders(userOrders)
                .build();
    }
}
