package dev.lewandowski.ordersapp.repository;

import dev.lewandowski.ordersapp.dto.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<ProductEntity, Long> {
}
