package com.perfulandia.perfulandia.Repository;
import com.perfulandia.perfulandia.Model.Client;
import com.perfulandia.perfulandia.Model.Logistics;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface LogisticsRepository extends JpaRepository<Logistics, Integer>{
}
