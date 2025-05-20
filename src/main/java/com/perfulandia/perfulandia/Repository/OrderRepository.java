package com.perfulandia.perfulandia.Repository;

import com.perfulandia.perfulandia.Model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Integer> {
}
