package uz.tsue.ricoin.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import uz.tsue.ricoin.dto.response.OrderDto;
import uz.tsue.ricoin.entity.Order;

@Component
@RequiredArgsConstructor
public class OrderMapper implements CustomMapperInterface<Order, OrderDto> {
    private final ProductMapper productMapper;

    @Override
    public OrderDto toDto(Order order) {
        return OrderDto.builder()
                .id(order.getId())
                .product(productMapper.toDto(order.getProduct()))
                .quantity(order.getQuantity())
                .price(order.getPrice())
                .orderStatus(order.getStatus())
                .build();
    }
}
