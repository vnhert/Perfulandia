package com.perfulandia.perfulandia.Repository;
import com.perfulandia.perfulandia.Model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.perfulandia.perfulandia.Model.Client;



@Repository
public interface ClientRepository extends JpaRepository<Client, Integer> {

}
