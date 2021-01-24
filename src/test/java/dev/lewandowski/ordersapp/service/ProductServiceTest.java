package dev.lewandowski.ordersapp.service;

import dev.lewandowski.ordersapp.dto.ProductEntity;
import dev.lewandowski.ordersapp.dto.converter.ProductConverter;
import dev.lewandowski.ordersapp.model.Product;
import dev.lewandowski.ordersapp.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    @InjectMocks
    ProductService productService;

    @Mock
    ProductRepository productRepository;
    @Mock
    ProductConverter productConverter;

    @Test
    void save() {
        Product product = buildProduct();
        ProductEntity productEntity = buildProductEntity();
        when(productRepository.save(productEntity)).thenReturn(productEntity);
        when(productConverter.convert(product)).thenReturn(productEntity);
        ProductEntity savedProductEntity = productService.save(product);
        assertNotNull(savedProductEntity);
    }

    @Test
    void findAll() {
        ProductEntity productEntity = buildProductEntity();
        List<ProductEntity> productEntities = Collections.singletonList(productEntity);
        Product product = buildProduct();
        List<Product> products = Collections.singletonList(product);
        when(productRepository.findAll()).thenReturn(productEntities);
        when(productConverter.convert(productEntities)).thenReturn(products);
        List<Product> convertedProducts = productService.findAll();
        assertNotNull(convertedProducts);
    }

    ProductEntity buildProductEntity() {
        String name = "Beer";
        BigDecimal price = new BigDecimal("20.20");
        ProductEntity productEntity = new ProductEntity();
        productEntity.setName(name);
        productEntity.setPrice(price);
        return productEntity;
    }

    Product buildProduct() {
        Long id = 1L;
        String name = "Beer";
        BigDecimal price = new BigDecimal("20.20");
        return new Product(id, name, price);
    }
}