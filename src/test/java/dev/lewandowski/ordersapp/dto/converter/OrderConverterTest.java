package dev.lewandowski.ordersapp.dto.converter;

import dev.lewandowski.ordersapp.dto.OrderEntity;
import dev.lewandowski.ordersapp.dto.ProductEntity;
import dev.lewandowski.ordersapp.model.Order;
import dev.lewandowski.ordersapp.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OrderConverterTest {

    @InjectMocks
    OrderConverter orderConverter;

    @Mock
    ProductRepository productRepository;

    @Test
    void convertTest() {
        Order order = buildOrder();
        ProductEntity testProductEntity = buildProductEntity();
        when(productRepository.findById(any())).thenReturn(java.util.Optional.ofNullable(testProductEntity));
        OrderEntity convertedOrder = orderConverter.convert(order);
        assertNotNull(convertedOrder);
        assertNotNull(convertedOrder.getReceiptEntity());
        assertEquals(order.getOrderedProducts().size(), convertedOrder.getOrderedProductEntities().size());
        assertFalse(convertedOrder.isPaid());
    }

    Order buildOrder() {
        List<Long> orderedProductsIds = buildLongList();
        return new Order(1L, orderedProductsIds);
    }

    List<Long> buildLongList() {
        return Collections.singletonList(1L);
    }

    ProductEntity buildProductEntity() {
        String name = "Beer";
        BigDecimal price = new BigDecimal("20.20");
        ProductEntity productEntity = new ProductEntity();
        productEntity.setId(1L);
        productEntity.setName(name);
        productEntity.setPrice(price);
        return productEntity;
    }
}