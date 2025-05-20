package com.perfulandia.perfulandia.Repository;

import com.perfulandia.perfulandia.Model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer> {
}
