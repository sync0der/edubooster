package uz.tsue.ricoin.service.impls;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import uz.tsue.ricoin.entity.Product;
import uz.tsue.ricoin.entity.ProductImage;
import uz.tsue.ricoin.repository.ProductImageRepository;
import uz.tsue.ricoin.service.interfaces.ProductImageService;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductImageServiceImpl implements ProductImageService {
    private final ProductImageRepository imageRepository;

    @Override
    public ProductImage get(Long id) {
        return imageRepository.findById(id).orElseThrow(null);
        //todo implement exception handling
    }

    @Override
    public List<ProductImage> getAll(Long productId) {
        return List.of();
    }

    @Override
    public void extractImage(Product product, MultipartFile file, ProductImage image) {
        try {
            if (file.getSize() != 0) {
                image = getImageEntity(file);
                image.setProduct(product);
                product.getImages().add(image);
            }
        }catch (Exception e){
            throw new RuntimeException();  // todo implement exception handling
        }
    }

    private ProductImage getImageEntity(MultipartFile file) throws IOException {
        return ProductImage.builder()
                .name(file.getName())
                .originalFileName(file.getOriginalFilename())
                .contentType(file.getContentType())
                .size(file.getSize())
                .bytes(file.getBytes())
                .build();
    }
}
