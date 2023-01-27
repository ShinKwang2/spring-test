  package com.lightshoes.springtest.product.domain;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "product")
public class ProductEntity {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long number;

    @Column(name = "product_name", nullable = false)
    String name;

    @Column(name = "product_price", nullable = false)
    private Integer price;

    @Column(name = "product_stock", nullable = false)
    private Integer stock;

    private LocalDateTime createdAt;

    private LocalDateTime lastModifiedAt;

    @Builder
    public ProductEntity(String name, Integer price, Integer stock) {
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.createdAt = LocalDateTime.now();
        this.lastModifiedAt = createdAt;
    }
}
