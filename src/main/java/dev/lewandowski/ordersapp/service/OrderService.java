package dev.lewandowski.ordersapp.service;

import dev.lewandowski.ordersapp.dto.OrderEntity;
import dev.lewandowski.ordersapp.dto.ReceiptEntity;
import dev.lewandowski.ordersapp.dto.converter.OrderConverter;
import dev.lewandowski.ordersapp.model.Order;
import dev.lewandowski.ordersapp.repository.OrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class OrderService {

  @Autowired
  private OrderRepository orderRepository;

  @Autowired
  private OrderConverter orderConverter;


  public OrderEntity save(Order order) {
    return orderRepository.save(orderConverter.convert(order));
  }

  public List<OrderEntity> findAll() {
    return orderRepository.findAll();
  }

  public ReceiptEntity getReceipt(Long id) {
    Optional<OrderEntity> orderEntityOptional = orderRepository.findById(id);
    if (orderEntityOptional.isEmpty()){
      log.warn("Asked for receipt for order which is not in database");
      return null;
    }
    orderEntityOptional.get().setPaid(true);
    orderRepository.save(orderEntityOptional.get());
    return orderEntityOptional.get().getReceiptEntity();
  }
}
