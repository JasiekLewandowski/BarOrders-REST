package dev.lewandowski.ordersapp.dto.converter;

import dev.lewandowski.ordersapp.dto.OrderEntity;
import dev.lewandowski.ordersapp.dto.ProductEntity;
import dev.lewandowski.ordersapp.dto.ReceiptEntity;
import dev.lewandowski.ordersapp.model.Order;
import dev.lewandowski.ordersapp.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Component
public class OrderConverter {

    @Autowired
    private ProductRepository productRepository;

    public OrderEntity convert(Order order) {
        List<ProductEntity> productEntities = new ArrayList<>();
        for (Long id : order.getOrderedProducts()) {
            Optional<ProductEntity> optionalEntity = productRepository.findById(id);
            if (optionalEntity.isPresent()) {
                ProductEntity productEntity = optionalEntity.get();
                productEntities.add(productEntity);
            } else {
                log.warn("Client is trying to order product with id: " + id + " which is not present in database");
            }
        }
        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setOrderedProductEntities(productEntities);
        orderEntity.setPaid(false);
        ReceiptEntity receiptEntity = createReceiptEntity(productEntities);
        orderEntity.setReceiptEntity(receiptEntity);
        return orderEntity;
    }

    private ReceiptEntity createReceiptEntity(List<ProductEntity> productEntities) {
        ReceiptEntity receiptEntity = new ReceiptEntity();
        BigDecimal totalPrice = new BigDecimal(0);
        for (ProductEntity productEntity : productEntities) {
            totalPrice = totalPrice.add(productEntity.getPrice());
        }
        receiptEntity.setTotalPrice(totalPrice);
        return receiptEntity;
    }
}
