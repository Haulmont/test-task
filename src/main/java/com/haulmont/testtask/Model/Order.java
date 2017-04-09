package com.haulmont.testtask.Model;

import lombok.Data;
import lombok.NonNull;

import javax.persistence.*;
import java.util.Date;

/**
 * Order Entity
 */
@Entity
@Table(name = "Orders")
@NamedQuery(name = "Order.findAll", query = "SELECT * FROM Orders o")
@Data(staticConstructor = "of")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @NonNull
    private Long ID;

    private String description;
    private final Customer customer;
    private Date createDate;
    private Date closeDate;
    private Money cost;
    private OrderStatus status;

}
