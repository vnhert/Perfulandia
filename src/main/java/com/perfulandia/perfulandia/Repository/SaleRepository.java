package com.perfulandia.perfulandia.Repository;

import com.perfulandia.perfulandia.Model.Sale;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SaleRepository extends JpaRepository<Sale, Integer> {

    List<Sale> findByClientId(Integer clientId);

}
