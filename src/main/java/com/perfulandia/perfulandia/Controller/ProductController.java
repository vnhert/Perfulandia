package com.perfulandia.perfulandia.Controller;
import com.perfulandia.perfulandia.Model.*;
import com.perfulandia.perfulandia.Service.ProductService;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;


@Tag(name = "Productos", description = "Operaciones relacionadas con los productos")
@RestController
@RequestMapping("/productos")
public class ProductController {


    @Autowired
    private ProductService productService;

    @Operation(summary = "Obtener todos los productos")
    @GetMapping
    public String getProducts(@RequestBody User solicitante) {
        return productService.getProducts(solicitante);
    }

    @Operation(summary = "Obtener todos los productos")
    @GetMapping("/catalogo")
    public String verProducts(@RequestBody User solicitante) {
        return productService.verProducts(solicitante);
    }

    @Operation(summary = "Obtener un producto por ID")
    @GetMapping("/{id}")
    public String getProduct(@RequestBody User solicitante, @PathVariable int id) {
        return productService.getProduct(solicitante, id);
    }
    @Operation(summary = "Agregar un nuevo producto")
    @PostMapping
    public String addProduct(@RequestBody ProductActionRequest request) {
        return productService.addProduct(request.getSolicitante(), request.getProduct());
    }
    @Operation(summary = "Actualizar un producto")
    @PutMapping("/{id}")
    public String updateProduct(@RequestBody ProductActionRequest request, @PathVariable int id) {
        return productService.updateProduct(request.getSolicitante(), id, request.getProduct());
    }

    @Operation(summary = "Eliminar un producto")
    @DeleteMapping("/{id}")
    public String deleteProduct(@RequestBody User solicitante, @PathVariable int id) {
        return productService.deleteProduct(solicitante, id);
    }

   }


