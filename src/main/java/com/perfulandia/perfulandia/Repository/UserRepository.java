package com.perfulandia.perfulandia.Repository;

import com.perfulandia.perfulandia.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;



public interface UserRepository extends JpaRepository<User, Integer> {

}
