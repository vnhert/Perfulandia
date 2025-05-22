package com.perfulandia.perfulandia.Controller;
import com.perfulandia.perfulandia.Model.*;
import com.perfulandia.perfulandia.Service.ProductService;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/productos")
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping
    public String getProducts(@RequestBody User solicitante) {
        return productService.getProducts(solicitante);
    }
    @GetMapping("/catalogo")
    public String verProducts(@RequestBody User solicitante) {
        return productService.verProducts(solicitante);
    }

    @GetMapping("/{id}")
    public String getProduct(@RequestBody User solicitante, @PathVariable int id) {
        return productService.getProduct(solicitante, id);
    }

    @PostMapping
    public String addProduct(@RequestBody ProductActionRequest request) {
        return productService.addProduct(request.getSolicitante(), request.getProduct());
    }

    @PutMapping("/{id}")
    public String updateProduct(@RequestBody ProductActionRequest request, @PathVariable int id) {
        return productService.updateProduct(request.getSolicitante(), id, request.getProduct());
    }

    @DeleteMapping("/{id}")
    public String deleteProduct(@RequestBody User solicitante, @PathVariable int id) {
        return productService.deleteProduct(solicitante, id);
    }

   }


