package com.pascadenis.honestee.entities;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "order_detail")
public class OrderDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_detail_id")
    private long id;

    private int quantity;

    private double totalPrice;

    private double unitPrice;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", referencedColumnName = "order_id")
    private Order order;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", referencedColumnName = "product_id")
    private Products product;
}
