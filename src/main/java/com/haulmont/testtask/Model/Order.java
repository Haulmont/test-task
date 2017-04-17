package com.haulmont.testtask.Model;

import lombok.*;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Cok on 09.04.2017.
 */
@Entity
@Table(name = "orders")
@NoArgsConstructor
@RequiredArgsConstructor
@ToString
@NamedQuery(name = "Order.findAll", query = "from Order")
@EqualsAndHashCode
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter
    @Setter
    private Long id;
    @Column
    @Getter
    @Setter
    @NonNull
    private String description;

    @Setter
    @NonNull
    @Type(type = "bigint")
    private Customer customer;

    @Column
    @Type(type = "timestamp")
    @Getter
    @Setter
    @NonNull
    private Date createDate;

    @Column(name = "enddate")
    @Type(type = "timestamp")
    @Getter
    @Setter
    private Date closeDate;
    @Column
    @Getter
    @Setter
    @NonNull
    private Double cost;
    @Column
    @Getter
    @Setter
    @NonNull
    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    @Access(AccessType.PROPERTY)
    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Customer.class)
    @JoinColumn(name = "CUSTOMER", nullable = false, foreignKey = @ForeignKey(name = "FK_CUSTOMER"))
    public Customer getCustomer() {
        return this.customer;
    }
}
