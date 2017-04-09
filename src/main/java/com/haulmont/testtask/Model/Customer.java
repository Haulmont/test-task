package com.haulmont.testtask.Model;

import lombok.*;

import javax.persistence.*;

/**
 * Customer Entity
 */
@Entity
@Table(name = "Customers")
@NamedNativeQueries(value =
        {
                @NamedNativeQuery(name = "Customer.findAll", query = "SELECT * FROM Customers c"),
                @NamedNativeQuery(name = "Customer.findById", query = "SELECT * FROM Customers c where id=:id")
        })

@Data
@NoArgsConstructor
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @NonNull
    @Column(unique = true, nullable = false)
    private Long ID;

    @Column(nullable = false)
    private String firstName;
    @Column(nullable = false)
    private String lastName;
    @Column
    private String familyName;
    @Column
    private String phoneNumber;

}
