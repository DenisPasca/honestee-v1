package com.pascadenis.honestee.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Data
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private long id;
    private Date orderDate;
    private Date deliveryDate;
    private double totalPrice;
    private double shippingFee;
    private String orderStatus;
    private String notes;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id",referencedColumnName = "user_id")
    private Users user;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "order")
    private List<OrderDetail> orderDetailList;
}
