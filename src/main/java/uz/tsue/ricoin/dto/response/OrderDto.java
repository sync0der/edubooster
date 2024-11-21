package uz.tsue.ricoin.dto.response;

import lombok.*;
import uz.tsue.ricoin.entity.enums.OrderStatus;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderDto {
    private Long id;
    private ProductDto product;
    private Integer quantity;
    private Integer price;
    private OrderStatus orderStatus;

}
