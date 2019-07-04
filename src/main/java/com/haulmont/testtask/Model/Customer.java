package com.haulmont.testtask.Model;

import lombok.*;

import javax.persistence.*;

/**
 * Created by Cok on 09.04.2017.
 */
@Entity
@Table(name = "customers")
@RequiredArgsConstructor
@NoArgsConstructor
@ToString
@NamedQuery(name = "Customer.findAll", query = "from Customer")
@EqualsAndHashCode
public class Customer {
    @Getter
    @Setter
    @NonNull
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column
    @Getter
    @Setter
    @NonNull
    private String firstName;
    @Column
    @Getter
    @Setter
    @NonNull
    private String lastName;
    @Column
    @Getter
    @Setter
    @NonNull
    private String thirdName;
    @Column
    @Getter
    @Setter
    @NonNull
    private String phone;
}
