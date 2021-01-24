package dev.lewandowski.ordersapp.service;

import dev.lewandowski.ordersapp.dto.OrderEntity;
import dev.lewandowski.ordersapp.dto.ProductEntity;
import dev.lewandowski.ordersapp.dto.ReceiptEntity;
import dev.lewandowski.ordersapp.dto.converter.OrderConverter;
import dev.lewandowski.ordersapp.model.Order;
import dev.lewandowski.ordersapp.repository.OrderRepository;
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
class OrderServiceTest {

    @InjectMocks
    OrderService orderService;

    @Mock
    OrderRepository orderRepository;
    @Mock
    OrderConverter orderConverter;

    @Test
    void save() {
        Order order = buildOrder();
        OrderEntity orderEntity = buildOrderEntity();
        when(orderRepository.save(orderEntity)).thenReturn(orderEntity);
        when(orderConverter.convert(order)).thenReturn(orderEntity);
        OrderEntity savedOrderEntity = orderService.save(order);
        assertNotNull(savedOrderEntity);
    }

    @Test
    void findAll() {
        OrderEntity orderEntity = buildOrderEntity();
        List<OrderEntity> orderEntities = Collections.singletonList(orderEntity);
        when(orderRepository.findAll()).thenReturn(orderEntities);
        List<OrderEntity> foundOrdersList = orderService.findAll();
        assertNotNull(foundOrdersList);
    }

    @Test
    void getReceipt() {
        OrderEntity orderEntity = buildOrderEntity();
        when(orderRepository.findById(any())).thenReturn(java.util.Optional.ofNullable(orderEntity));
        ReceiptEntity receiptEntity = orderService.getReceipt(orderEntity.getReceiptEntity().getId());
        assertTrue(orderEntity.isPaid());
        assertNotNull(receiptEntity);
        assertEquals(orderEntity.getReceiptEntity().getTotalPrice(), receiptEntity.getTotalPrice());
    }

    Order buildOrder() {
        List<Long> orderedProductsIds = buildLongList();
        return new Order(1L, orderedProductsIds);
    }

    OrderEntity buildOrderEntity(){
        List<ProductEntity> orderedProducts = buildProductEntityList();
        OrderEntity orderEntity = new OrderEntity();
        ReceiptEntity receiptEntity = buildReceiptEntity();
        orderEntity.setId(1L);
        orderEntity.setOrderedProductEntities(orderedProducts);
        orderEntity.setReceiptEntity(receiptEntity);
        orderEntity.setPaid(false);
        return  orderEntity;
    }

    List<Long> buildLongList (){
        return Collections.singletonList(1L);
    }

    ProductEntity buildProductEntity(){
        String name = "Beer";
        BigDecimal price = new BigDecimal("20.20");
        ProductEntity productEntity = new ProductEntity();
        productEntity.setId(1L);
        productEntity.setName(name);
        productEntity.setPrice(price);
        return productEntity;
    }
    List<ProductEntity> buildProductEntityList() {
        ProductEntity productEntity = buildProductEntity();
        return Collections.singletonList(productEntity);
    }
    ReceiptEntity buildReceiptEntity () {
        Long id = 1L;
        BigDecimal totalPrice = new BigDecimal("20.20");
        ReceiptEntity receiptEntity = new ReceiptEntity();
        receiptEntity.setId(id);
        receiptEntity.setTotalPrice(totalPrice);
        return receiptEntity;
    }
}