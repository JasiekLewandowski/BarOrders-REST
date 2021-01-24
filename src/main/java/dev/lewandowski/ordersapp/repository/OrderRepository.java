package dev.lewandowski.ordersapp.repository;

import dev.lewandowski.ordersapp.dto.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<OrderEntity, Long> {

}
