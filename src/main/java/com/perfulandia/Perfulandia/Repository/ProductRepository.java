package com.perfulandia.Perfulandia.Repository;

import com.perfulandia.Perfulandia.Model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer> {
}
