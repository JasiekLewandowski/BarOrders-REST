package dev.lewandowski.ordersapp.controller;

import dev.lewandowski.ordersapp.dto.OrderEntity;
import dev.lewandowski.ordersapp.dto.ReceiptEntity;
import dev.lewandowski.ordersapp.model.Order;
import dev.lewandowski.ordersapp.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/addOrder")
    public OrderEntity addOrder(@RequestBody Order order) {
        log.info("Placing order");
        return orderService.save(order);
    }

    @GetMapping("/getAllOrders")
    public List<OrderEntity> getAllOrders() {
        log.info("Getting list of all orders");
        return orderService.findAll();
    }

    @GetMapping("/getReceipt")
    public ReceiptEntity getReceipt(Long id) {
        return orderService.getReceipt(id);
    }
}
