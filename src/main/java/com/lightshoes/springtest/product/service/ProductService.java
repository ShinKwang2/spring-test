package com.lightshoes.springtest.product.service;

import com.lightshoes.springtest.product.domain.ProductEntity;
import com.lightshoes.springtest.product.dto.request.ProductCreate;
import com.lightshoes.springtest.product.dto.response.ProductResponse;
import com.lightshoes.springtest.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ProductService {

    private final Logger LOGGER = LoggerFactory.getLogger(ProductService.class);
    private final ProductRepository productRepository;

    public ProductResponse getProduct(Long number) {
        LOGGER.info("[getProduct] input number : {}", number);
        ProductEntity product = productRepository.findById(number).get();

        LOGGER.info("[getProduct] product number : {}, name : {}", product.getNumber(), product.getName());
        ProductResponse productResponse = new ProductResponse();
        productResponse.setNumber(product.getNumber());
        productResponse.setName(product.getName());
        productResponse.setPrice(product.getPrice());
        productResponse.setStock(product.getStock());

        return productResponse;
    }

    public ProductResponse saveProduct(ProductCreate productCreate) {
        LOGGER.info("[saveProduct] productCreate : {}", productCreate.toString());
        ProductEntity product = new ProductEntity(productCreate.getName(), productCreate.getPrice(), productCreate.getStock());

        ProductEntity savedProduct = productRepository.save(product);
        LOGGER.info("[saveProduct] saveProduct : {}", savedProduct);

        ProductResponse productResponse = new ProductResponse();
        productResponse.setNumber(product.getNumber());
        productResponse.setName(product.getName());
        productResponse.setPrice(product.getPrice());
        productResponse.setStock(product.getStock());

        return productResponse;
    }

    public ProductResponse changeProductName(Long number, String name) {
        ProductEntity foundProduct = productRepository.findById(number).get();
        foundProduct.setName(name);
        ProductEntity changeProduct = productRepository.save(foundProduct);

        ProductResponse productResponse = new ProductResponse();
        productResponse.setNumber(changeProduct.getNumber());
        productResponse.setName(changeProduct.getName());
        productResponse.setPrice(changeProduct.getPrice());
        productResponse.setStock(changeProduct.getStock());

        return productResponse;
    }

    public void deleteProduct(Long number) {
        productRepository.deleteById(number);
    }
}
