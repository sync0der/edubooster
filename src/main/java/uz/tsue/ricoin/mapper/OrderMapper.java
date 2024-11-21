package uz.tsue.ricoin.mapper;

import org.springframework.stereotype.Component;
import uz.tsue.ricoin.dto.response.OrderDto;
import uz.tsue.ricoin.entity.Order;

@Component
public class OrderMapper implements CustomMapperInterface<Order, OrderDto> {
    @Override
    public OrderDto toDto(Order order) {
        return OrderDto.builder()
                .id(order.getId())
                .product(order.getProduct())
                .quantity(order.getQuantity())
                .price(order.getPrice())
                .orderStatus(order.getStatus())
                .build();
    }
}
