package com.perfulandia.perfulandia.Service;
import com.perfulandia.perfulandia.Model.Product;
import com.perfulandia.perfulandia.Model.User;
import com.perfulandia.perfulandia.Repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {
    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductService productService;

    @Test
    void testGetProducts() {
        User solicitante = new User();
        solicitante.setNombre("admin");
        when(solicitante.puedeGestionarProductos()).thenReturn(true);

        when(productRepository.findAll()).thenReturn(List.of(new Product()));

        String result = productService.getProducts(solicitante);

        assertNotNull(result);
        assertTrue(result.contains("ID del producto"));
        verify(productRepository, times(1)).findAll();
    }

    @Test
    void testAddProduct() {
        User solicitante = new User();
        solicitante.setNombre("admin");
        when(solicitante.puedeGestionarProductos()).thenReturn(true);

        Product product = new Product();
        when(productRepository.save(product)).thenReturn(product);

        String result = productService.addProduct(solicitante, product);

        assertEquals("Producto agregado con éxito", result);
        verify(productRepository, times(1)).save(product);
    }

    @Test
    void testGetProduct() {
        User solicitante = new User();
        solicitante.setNombre("admin");
        when(solicitante.puedeGestionarProductos()).thenReturn(true);

        Product product = new Product();
        product.setId(1);
        when(productRepository.findById(1)).thenReturn(Optional.of(product));

        String result = productService.getProduct(solicitante, 1);

        assertNotNull(result);
        assertTrue(result.contains("ID del producto: 1"));
        verify(productRepository, times(1)).findById(1);
    }

    @Test
    void testDeleteProduct() {
        User solicitante = new User();
        solicitante.setNombre("admin");
        when(solicitante.puedeGestionarProductos()).thenReturn(true);

        when(productRepository.existsById(1)).thenReturn(true);

        String result = productService.deleteProduct(solicitante, 1);

        assertEquals("Producto eliminado con éxito", result);
        verify(productRepository, times(1)).deleteById(1);
    }

    @Test
    void testUpdateProduct() {
        User solicitante = new User();
        solicitante.setNombre("admin");
        when(solicitante.puedeGestionarProductos()).thenReturn(true);

        Product existingProduct = new Product();
        existingProduct.setId(1);
        Product updatedProduct = new Product();
        updatedProduct.setNombre("Producto Actualizado");

        when(productRepository.findById(1)).thenReturn(Optional.of(existingProduct));
        when(productRepository.save(existingProduct)).thenReturn(updatedProduct);

        String result = productService.updateProduct(solicitante, 1, updatedProduct);

        assertEquals("Producto actualizado con éxito", result);
        verify(productRepository, times(1)).save(existingProduct);
    }
}
