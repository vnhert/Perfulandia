package com.perfulandia.perfulandia.Controller;
import com.perfulandia.perfulandia.Model.Branch;

import com.perfulandia.perfulandia.Model.BranchActionRequest;
import com.perfulandia.perfulandia.Model.User;
import com.perfulandia.perfulandia.Service.BranchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/sucursales")
public class BranchController {
    @Autowired
    private BranchService branchService;

    @GetMapping
    public String getBranchs(@RequestBody User solicitante) {
        return branchService.getBranchs(solicitante);
    }

    @GetMapping("/{id}")
    public String getBranch(@RequestBody User solicitante, @PathVariable int id) {
        return branchService.getBranch(solicitante, id);
    }

    @PostMapping
    public String addBranch(@RequestBody BranchActionRequest request) {
        return branchService.addBranch(request.getSolicitante(), request.getBranch());
    }

    @PutMapping("/{id}")
    public String updateBranch(@RequestBody BranchActionRequest request, @PathVariable int id) {
        return branchService.updateBranch(request.getSolicitante(), id, request.getBranch());
    }

    @DeleteMapping("/{id}")
    public String deleteBranch(@RequestBody User solicitante, @PathVariable int id) {
        return branchService.deleteBranch(solicitante, id);
    }
}
