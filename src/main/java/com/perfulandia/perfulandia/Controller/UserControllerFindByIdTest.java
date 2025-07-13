package com.perfulandia.perfulandia.Controller;

import com.perfulandia.perfulandia.Model.GerenteSucursal;
import com.perfulandia.perfulandia.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;


import java.util.Optional;

import static java.nio.file.Paths.get;

@WebMvcTest(UserController.class)
public class UserControllerFindByIdTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean private UserService userService;

    @Test
    void testGetUserById() throws Exception {
        GerenteSucursal user = new GerenteSucursal();
        user.setId(1);
        user.setNombre("Pedro");
        user.setCorreo("pedro@mail.com");
        user.setContraseña("password");
        when(userService.findById(1L)).thenReturn(Optional.of(user));

        mockMvc.perform(get("/users/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre").value("Pedro"));
    }
}
