package dev.lewandowski.ordersapp.dto.converter;

import dev.lewandowski.ordersapp.dto.ProductEntity;
import dev.lewandowski.ordersapp.model.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ProductConverterTest {
    private ProductConverter productConverter;

    @BeforeEach
    void setUp() {
        productConverter = new ProductConverter();
    }

    @Test
    void convertProductToProductEntityTest() {
        Product product = new Product(1L, "Beer", new BigDecimal("20.20"));
        ProductEntity productEntity = productConverter.convert(product);
        assertEquals(product.getPrice(), productEntity.getPrice());
        assertEquals(product.getName(), productEntity.getName());
    }

    @Test
    void convertProductEntityToProductTest() {
        ProductEntity productEntity = buildProductEntity();
        Product product = productConverter.convert(productEntity);
        assertEquals(productEntity.getName(), product.getName());
        assertEquals(productEntity.getPrice(), product.getPrice());
    }

    @Test
    void convertProductEntityListIntoProductListTest() {
        ProductEntity productEntity = buildProductEntity();
        List<ProductEntity> productEntities = Collections.singletonList(productEntity);
        List<Product> products = productConverter.convert(productEntities);
        assertEquals(productEntity.getName(), products.get(0).getName());
        assertEquals(productEntity.getPrice(), products.get(0).getPrice());
    }

    ProductEntity buildProductEntity() {
        String name = "Beer";
        BigDecimal price = new BigDecimal("20.20");
        ProductEntity productEntity = new ProductEntity();
        productEntity.setName(name);
        productEntity.setPrice(price);
        return productEntity;
    }
}