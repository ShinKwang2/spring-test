package com.lightshoes.springtest.product.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ProductResponse {

    private Long number;

    private String name;

    private Integer price;

    private Integer stock;
}
