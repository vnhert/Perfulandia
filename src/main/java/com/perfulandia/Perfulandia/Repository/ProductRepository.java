package com.perfulandia.perfulandia.repository;

import com.perfulandia.perfulandia.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer> {
}
