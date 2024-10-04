package com.example.demo.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "orders")
public class Order implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String info;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(
            name = "cities",
            joinColumns = @JoinColumn(name = "order_id")
    )
    @Column(name = "city")
    private List<String> cities = new ArrayList<>();
}
