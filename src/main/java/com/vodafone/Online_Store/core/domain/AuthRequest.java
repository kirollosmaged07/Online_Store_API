package com.vodafone.Online_Store.core.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthRequest {
    private String username;
    private String password;

    @Entity
    @Table(name = "orders") // Avoid reserved keyword
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Order {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "order_id")
        private long id;

        private Date date;

        private String status;

        @ManyToOne
        @JoinColumn(name = "user_id") // Foreign key column for the User entity
        private User user;

        @ManyToMany
        @JoinTable(
                name = "order_products",
                joinColumns = @JoinColumn(name = "order_id"),
                inverseJoinColumns = @JoinColumn(name = "product_id")
        )
        private Set<Product> products = new HashSet<>();
    }
}
