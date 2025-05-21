package com.perfulandia.perfulandia.Controller;
import com.perfulandia.perfulandia.Model.Product;
import com.perfulandia.perfulandia.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/productos")
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping
    public String getProducts() {
        return productService.getProducts();
    }

    @GetMapping("/{id}")
    public String getProduct(@PathVariable int id) {
        return productService.getProduct(id);
    }

    @PostMapping
    public String addProduct(@RequestBody Product product) {
        return productService.addProduct(product);
    }

    @DeleteMapping("/{id}")
    public String deleteProduct(@PathVariable int id) {
        return productService.deleteProduct(id);
    }

    @PutMapping("/{id}")
    public String updateProduct(@PathVariable int id, @RequestBody Product product) {
        return productService.updateProduct(id, product);
    }
}
