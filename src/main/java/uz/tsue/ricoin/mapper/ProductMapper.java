package uz.tsue.ricoin.mapper;

import org.springframework.stereotype.Component;
import uz.tsue.ricoin.dto.response.ProductDto;
import uz.tsue.ricoin.entity.Product;

@Component
public class ProductMapper implements CustomMapperInterface<Product, ProductDto> {

    @Override
    public ProductDto toDto(Product product) {
        return ProductDto.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .imageUrl(product.getImgUrl())
                .price(product.getPrice())
                .availableQuantity(product.getAvailableQuantity())
                .build();
    }

    @Override
    public Product toEntity(ProductDto productDto) {
        return Product.builder()
                .name(productDto.getName())
                .description(productDto.getDescription())
                .price(productDto.getPrice())
                .imgUrl(productDto.getImageUrl())
                .availableQuantity(productDto.getAvailableQuantity())
                .build();
    }
}
