package com.lightshoes.springtest.product.repository;

import com.lightshoes.springtest.product.domain.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<ProductEntity, Long> {
}
