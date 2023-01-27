package com.lightshoes.springtest.product.dto.request;

import lombok.Builder;
import lombok.Data;

@Data
public class ProductCreate {

    private String name;

    private Integer price;

    private Integer stock;

    @Builder
    public ProductCreate(String name, Integer price, Integer stock) {
        this.name = name;
        this.price = price;
        this.stock = stock;
    }
}
