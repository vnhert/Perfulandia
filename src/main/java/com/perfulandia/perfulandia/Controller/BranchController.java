package com.perfulandia.perfulandia.Controller;
import com.perfulandia.perfulandia.Model.Branch;
import com.perfulandia.perfulandia.Model.Product;
import com.perfulandia.perfulandia.Service.BranchService;
import com.perfulandia.perfulandia.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

public class BranchController {
    @Autowired
    private BranchService branchService;

    @GetMapping
    public String getBranchs() {
        return branchService.getBranchs();
    }

    @GetMapping("/{id}")
    public String getBranch(@PathVariable int id) {
        return branchService.getBranch(id);
    }

    @PostMapping
    public String addBranch(@RequestBody Branch branch) {
        return branchService.addBranch(branch);
    }

    @DeleteMapping("/{id}")
    public String deleteBranch(@PathVariable int id) {
        return branchService.deleteBranch(id);
    }

    @PutMapping("/{id}")
    public String updateBranch(@PathVariable int id, @RequestBody Branch branch) {
        return branchService.updateBranch(id,branch);
    }
}
