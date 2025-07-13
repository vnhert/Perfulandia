package com.perfulandia.perfulandia.Controller;

import com.perfulandia.perfulandia.Model.Product;
import com.perfulandia.perfulandia.Model.ProductActionRequest;
import com.perfulandia.perfulandia.Model.User;
import com.perfulandia.perfulandia.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Tag(name = "Productos", description = "Operaciones relacionadas con los productos")
@RestController
@RequestMapping("/productos")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Operation(summary = "Obtener todos los productos")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Lista obtenida con éxito"),
            @ApiResponse(responseCode = "403", description = "No autorizado")
    })
    @GetMapping
    public CollectionModel<EntityModel<Product>> getProducts(@RequestBody User solicitante) {
        List<EntityModel<Product>> products = productService.getProducts(solicitante).stream()
                .map(product -> EntityModel.of(product,
                        linkTo(methodOn(ProductController.class).getProduct(solicitante, product.getId())).withSelfRel(),
                        linkTo(methodOn(ProductController.class).getProducts(solicitante)).withRel("productos")))
                .collect(Collectors.toList());

        return CollectionModel.of(products,
                linkTo(methodOn(ProductController.class).getProducts(solicitante)).withSelfRel());
    }

    @Operation(summary = "Obtener todos los productos (catálogo)")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Catálogo obtenido con éxito"),
            @ApiResponse(responseCode = "403", description = "No autorizado")
    })
    @GetMapping("/catalogo")
    public CollectionModel<EntityModel<Product>> verProducts(@RequestBody User solicitante) {
        List<EntityModel<Product>> catalog = productService.verProducts(solicitante).stream()
                .map(product -> EntityModel.of(product,
                        linkTo(methodOn(ProductController.class).getProduct(solicitante, product.getId())).withSelfRel(),
                        linkTo(methodOn(ProductController.class).verProducts(solicitante)).withRel("catalogo")))
                .collect(Collectors.toList());

        return CollectionModel.of(catalog,
                linkTo(methodOn(ProductController.class).verProducts(solicitante)).withSelfRel());
    }

    @Operation(summary = "Obtener un producto por ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Producto obtenido con éxito"),
            @ApiResponse(responseCode = "404", description = "Producto no encontrado")
    })
    @GetMapping("/{id}")
    public EntityModel<Product> getProduct(@RequestBody User solicitante, @PathVariable int id) {
        Product product = productService.getProduct(solicitante, id);
        return EntityModel.of(product,
                linkTo(methodOn(ProductController.class).getProduct(solicitante, id)).withSelfRel(),
                linkTo(methodOn(ProductController.class).getProducts(solicitante)).withRel("productos"));
    }

    @Operation(summary = "Agregar un nuevo producto")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Producto creado con éxito"),
            @ApiResponse(responseCode = "400", description = "Solicitud inválida")
    })
    @PostMapping
    public String addProduct(@RequestBody ProductActionRequest request) {
        return productService.addProduct(request.getSolicitante(), request.getProduct());
    }

    @Operation(summary = "Actualizar un producto")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Producto actualizado con éxito"),
            @ApiResponse(responseCode = "404", description = "Producto no encontrado")
    })
    @PutMapping("/{id}")
    public String updateProduct(@RequestBody ProductActionRequest request, @PathVariable int id) {
        return productService.updateProduct(request.getSolicitante(), id, request.getProduct());
    }

    @Operation(summary = "Eliminar un producto")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Producto eliminado con éxito"),
            @ApiResponse(responseCode = "404", description = "Producto no encontrado")
    })
    @DeleteMapping("/{id}")
    public String deleteProduct(@RequestBody User solicitante, @PathVariable int id) {
        return productService.deleteProduct(solicitante, id);
    }
}


