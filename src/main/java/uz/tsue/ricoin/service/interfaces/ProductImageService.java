package uz.tsue.ricoin.service.interfaces;

import org.springframework.web.multipart.MultipartFile;
import uz.tsue.ricoin.entity.Product;
import uz.tsue.ricoin.entity.ProductImage;

import java.awt.*;
import java.io.IOException;
import java.util.List;

public interface ProductImageService {
    ProductImage get(Long id);

    List<ProductImage> getAll(Long productId);

    void extractImage(Product product, MultipartFile file, ProductImage image);


}
