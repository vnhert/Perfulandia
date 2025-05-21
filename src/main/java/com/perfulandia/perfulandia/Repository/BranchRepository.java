package com.perfulandia.perfulandia.Repository;
import com.perfulandia.perfulandia.Model.Branch;
import org.springframework.data.jpa.repository.JpaRepository;


public interface BranchRepository extends JpaRepository<Branch,Integer> {
    // Custom query methods can be defined here if needed
    // For example, findByName(String name);
}
