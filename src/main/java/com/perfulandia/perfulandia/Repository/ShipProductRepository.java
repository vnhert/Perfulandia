package com.perfulandia.perfulandia.Repository;

import com.perfulandia.perfulandia.Model.ShipProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShipProductRepository extends JpaRepository<ShipProduct, Integer> {
}
