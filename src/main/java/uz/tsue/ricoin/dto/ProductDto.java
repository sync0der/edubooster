package uz.tsue.ricoin.dto;

import lombok.*;
import uz.tsue.ricoin.entity.ProductImage;

import java.util.List;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {
    private Long id;
    private String name;
    private String description;
    private int price;
    private List<ProductImage> images;
    private int availableQuantity;
}
