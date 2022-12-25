package com.leonardus.kasadalu.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "clients")
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String lastname;

    private String phone;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(referencedColumnName = "id", name = "client_id")
    private List<Order> orders;
}
