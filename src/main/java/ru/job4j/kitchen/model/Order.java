package ru.job4j.kitchen.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "orders")
@Getter
@Setter
@ToString
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;
    private String descriptionOrder;
    @Column(name = "status_order")
    private String statusOrder;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "order_products",
        joinColumns = @JoinColumn (name = "id_order"),
            inverseJoinColumns = @JoinColumn (name = "id_product")
    )
    private Set<Dish> set = new java.util.HashSet<>();
}