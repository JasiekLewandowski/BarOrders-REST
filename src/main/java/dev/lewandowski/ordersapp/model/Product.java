package dev.lewandowski.ordersapp.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@AllArgsConstructor
@Getter
@Setter
public class Product {
    private Long id;
    private String name;
    private BigDecimal price;
}
