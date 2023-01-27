package com.lightshoes.springtest.product.controller;

import com.lightshoes.springtest.product.dto.request.ProductCreate;
import com.lightshoes.springtest.product.dto.response.ProductResponse;
import com.lightshoes.springtest.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/product")
public class ProductController {

    private final ProductService productService;

    @GetMapping
    public ResponseEntity<ProductResponse> getProduct(Long number) {
        ProductResponse response = productService.getProduct(number);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping
    public ResponseEntity<ProductResponse> createProduct(@RequestBody ProductCreate productCreate) {
        ProductResponse productResponse = productService.saveProduct(productCreate);

        return ResponseEntity.status(HttpStatus.OK).body(productResponse);
    }
}
