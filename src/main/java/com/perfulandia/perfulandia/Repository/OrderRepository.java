package com.perfulandia.perfulandia.Repository;

import com.perfulandia.perfulandia.Model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Integer> {

    List<Order> findByEstado(String estado);

}
