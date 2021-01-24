package dev.lewandowski.ordersapp.dto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class ReceiptEntityTest {

    private ReceiptEntity receiptEntity;

    @BeforeEach
    void setUp() {
        receiptEntity = new ReceiptEntity();
    }

    @Test
    void testIdSetter() {
        receiptEntity.setId(4L);
        assertEquals(4L, receiptEntity.getId());
    }

    @Test
    void testTotalPriceSetter() {
        receiptEntity.setTotalPrice(new BigDecimal("20.20"));
        BigDecimal testBigDecimal = new BigDecimal("20.20");
        assertEquals(testBigDecimal, receiptEntity.getTotalPrice());
    }
}