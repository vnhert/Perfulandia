package com.perfulandia.Perfulandia.Repository;

import com.perfulandia.Perfulandia.Model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Integer> {
}
