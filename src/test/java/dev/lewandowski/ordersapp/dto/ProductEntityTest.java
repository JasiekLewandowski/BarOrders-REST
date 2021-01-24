package dev.lewandowski.ordersapp.dto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class ProductEntityTest {

    private ProductEntity productEntity;

    @BeforeEach
    void setUp() {
        productEntity = new ProductEntity();
    }

    @Test
    void testIdSetter() {
        productEntity.setId(4L);
        assertEquals(4L, productEntity.getId());
    }

    @Test
    void setPriceSetter() {
        productEntity.setPrice(new BigDecimal("20.20"));
        BigDecimal testDecimal = new BigDecimal("20.20");
        assertEquals(testDecimal, productEntity.getPrice());
    }

    @Test
    void setNameSetter() {
        productEntity.setName("Beer");
        assertEquals("Beer", productEntity.getName());
    }
}