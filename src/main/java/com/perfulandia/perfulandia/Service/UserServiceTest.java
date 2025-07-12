package com.perfulandia.perfulandia.Service;

import com.perfulandia.perfulandia.Model.User;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
    @Mock private UserRepository userRepo;
    @InjectMocks private UserService userService;

    @Test
    void testFindById() {
        EmpleadoVentas user = new EmpleadoVentas();
        user.setId(1);
        user.setNombre("Pedro");
        user.setCorreo("pedro@mail.com");
        user.setContraseña("password");
        when(userRepo.findById(1L)).thenReturn(Optional.of(user));

        Optional<User> result = userService.findById(1L);

        assertTrue(result.isPresent());
        assertEquals("Pedro", result.get().getNombre());
    }
}

