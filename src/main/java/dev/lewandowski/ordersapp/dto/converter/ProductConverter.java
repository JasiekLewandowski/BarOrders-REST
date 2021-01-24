package dev.lewandowski.ordersapp.dto.converter;

import dev.lewandowski.ordersapp.dto.ProductEntity;
import dev.lewandowski.ordersapp.model.Product;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProductConverter {

    public ProductEntity convert(Product product) {
        ProductEntity productEntity = new ProductEntity();
        productEntity.setName(product.getName());
        productEntity.setPrice(product.getPrice());
        return productEntity;
    }

    public Product convert(ProductEntity productEntity) {
        return new Product(productEntity.getId(), productEntity.getName(), productEntity.getPrice());
    }

    public List<Product> convert(List<ProductEntity> productEntities) {
        return productEntities.stream().map(this::convert).collect(Collectors.toList());
    }
}
