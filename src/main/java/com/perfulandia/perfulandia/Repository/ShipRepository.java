package com.perfulandia.perfulandia.Repository;

import com.perfulandia.perfulandia.Model.Ship;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShipRepository extends JpaRepository<Ship, Integer> {
}
