package com.haulmont.testtask.Model;

import lombok.Data;
import lombok.NonNull;
import org.hibernate.annotations.Type;

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
    @Column(unique = true, nullable = false)
    private Long ID;

    @Column(nullable = false, length = 1000)
    private String description;

    private final Customer customer;

    @Type(type="timestamp")
    @Column(nullable = false)
    private Date createDate;

    @Type(type = "timestamp")
    @Column
    private Date closeDate;

    @Column
    private Double cost;
    private OrderStatus status;

}
