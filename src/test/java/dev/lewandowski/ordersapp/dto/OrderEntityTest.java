package dev.lewandowski.ordersapp.dto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class OrderEntityTest {

    private OrderEntity orderEntity;

    @BeforeEach
    void setUp() {
        orderEntity = new OrderEntity();
    }

    @Test
    void testIdSetter() {
        Long idValue = 4L;
        orderEntity.setId(idValue);
        assertEquals(idValue, orderEntity.getId());
    }

    @Test
    void testReceiptEntity() {
        ReceiptEntity receiptEntity = new ReceiptEntity();
        orderEntity.setReceiptEntity(receiptEntity);
        assertEquals(receiptEntity, orderEntity.getReceiptEntity());
    }

    @Test
    void testOrderedProductEntitiesList() {
        List<ProductEntity> orderedProductEntities = new ArrayList<>();
        ProductEntity productEntity = new ProductEntity();
        orderedProductEntities.add(productEntity);
        orderEntity.setOrderedProductEntities(orderedProductEntities);
        assertEquals(orderedProductEntities, orderEntity.getOrderedProductEntities());
    }

    @Test
    void testIsPaidBoolean() {
        orderEntity.setPaid(true);
        assertTrue(orderEntity.isPaid());
    }
}