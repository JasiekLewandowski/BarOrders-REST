package dev.lewandowski.ordersapp.dto;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "bar_order")
@Getter
@Setter
public class OrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne(cascade = {CascadeType.ALL})
    private ReceiptEntity receiptEntity;

    @OneToMany(cascade = {CascadeType.ALL})
    private List<ProductEntity> orderedProductEntities = new ArrayList<>();

    private boolean isPaid;

}
