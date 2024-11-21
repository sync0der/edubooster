package uz.tsue.ricoin.service.impls;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import uz.tsue.ricoin.dto.response.ProductDto;
import uz.tsue.ricoin.entity.Product;
import uz.tsue.ricoin.mapper.ProductMapper;
import uz.tsue.ricoin.repository.ProductRepository;
import uz.tsue.ricoin.service.interfaces.ProductService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    @Override
    public ProductDto get(Long id) {
        Product product = findById(id);
        return productMapper.toDto(product);
    }

    @Override
    public List<ProductDto> getAll() {
        List<Product> all = productRepository.findAll();
        List<ProductDto> products = new ArrayList<>();
        for (Product product : all) {
            products.add(productMapper.toDto(product));
        }
        return products;
    }

    @Override
    public ProductDto create(ProductDto productDto, MultipartFile file) {
        Product product = productMapper.toEntity(productDto);
        productRepository.save(product);
        return productMapper.toDto(product);
    }

    @Override
    public void update(Long id, ProductDto productDto) {
        Product product = findById(id);
        if (productDto != null) {
            Optional.ofNullable(productDto.getName()).ifPresent(product::setName);
            Optional.ofNullable(productDto.getDescription()).ifPresent(product::setDescription);
            Optional.ofNullable(productDto.getPrice()).ifPresent(product::setPrice);
            Optional.ofNullable(productDto.getImageUrl()).ifPresent(product::setImgUrl);
            Optional.ofNullable(productDto.getAvailableQuantity()).ifPresent(product::setAvailableQuantity);
        }
        productRepository.save(product);
    }

    @Override
    public void remove(Long id) {
        productRepository.delete(findById(id));
    }

    @Override
    public boolean isStockAvailable(Product product, int requiredQuantity) {
        return product.getAvailableQuantity() >= requiredQuantity;
    }

    @Override
    public void addProductQuantity(Product product, int inputQuantity) {
        product.setAvailableQuantity(product.getAvailableQuantity() + inputQuantity);
        productRepository.save(product);
    }

    @Override
    public Product findById(Long id) {
        return productRepository.findById(id)
                .orElseThrow();
        // todo implement exception handling
    }
}
