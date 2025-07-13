package com.perfulandia.perfulandia.Controller;

import com.perfulandia.perfulandia.Model.Product;
import com.perfulandia.perfulandia.Model.User;
import com.perfulandia.perfulandia.Service.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ProductController.class)
public class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductService productService;

    @Test
    void testGetAllProducts() throws Exception {
        when(productService.getProducts(any(User.class))).thenReturn("Lista de productos");

        mockMvc.perform(get("/productos")
                        .contentType("application/x-www-form-urlencoded")
                        .content("nombre=admin"))
                .andExpect(status().isOk())
                .andExpect(content().string("Lista de productos"));
    }

    @Test
    void testGetProductById() throws Exception {
        when(productService.getProduct(any(User.class), eq(1))).thenReturn("Detalles del producto");

        mockMvc.perform(get("/productos/1")
                        .contentType("application/x-www-form-urlencoded")
                        .content("nombre=admin"))
                .andExpect(status().isOk())
                .andExpect(content().string("Detalles del producto"));
    }

    @Test
    void testAddProduct() throws Exception {
        when(productService.addProduct(any(User.class), any(Product.class))).thenReturn("Producto agregado con éxito");

        mockMvc.perform(post("/productos")
                        .contentType("application/x-www-form-urlencoded")
                        .content("solicitante=admin&nombreProducto=Producto+Nuevo"))
                .andExpect(status().isOk())
                .andExpect(content().string("Producto agregado con éxito"));
    }

    @Test
    void testUpdateProduct() throws Exception {
        when(productService.updateProduct(any(User.class), eq(1), any(Product.class))).thenReturn("Producto actualizado con éxito");

        mockMvc.perform(put("/productos/1")
                        .contentType("application/x-www-form-urlencoded")
                        .content("solicitante=admin&nombreProducto=Producto+Actualizado"))
                .andExpect(status().isOk())
                .andExpect(content().string("Producto actualizado con éxito"));
    }

    @Test
    void testDeleteProduct() throws Exception {
        when(productService.deleteProduct(any(User.class), eq(1))).thenReturn("Producto eliminado con éxito");

        mockMvc.perform(delete("/productos/1")
                        .contentType("application/x-www-form-urlencoded")
                        .content("nombre=admin"))
                .andExpect(status().isOk())
                .andExpect(content().string("Producto eliminado con éxito"));
    }
}