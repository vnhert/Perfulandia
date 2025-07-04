package com.perfulandia.perfulandia.Controller;
import com.perfulandia.perfulandia.Model.User;
import com.perfulandia.perfulandia.Model.UserActionRequest;
import com.perfulandia.perfulandia.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;

@Tag(name = "Usuarios", description = "Operaciones relacionadas con los usuarios")
@RestController
@RequestMapping("/usuarios")
public class  UserController {
    @Autowired
    UserService userService;


    @Operation(summary = "Obtener todos los usuarios")
    @GetMapping ()
    public String getUsers(@RequestBody User solicitante){
        return userService.getUsers(solicitante);
    }

    @Operation(summary = "Obtener un usuario por ID")
    @GetMapping("/{id}")
    public String getUserById(@RequestBody User solicitante, @PathVariable int id){
        return userService.getUser(solicitante, id);
    }
    @Operation(summary = "Registrar un nuevo usuario")
    @PostMapping()
    public String addUser(@RequestBody UserActionRequest request){
        return userService.addUser(request.getSolicitante(), request.getUser());
    }
    @Operation(summary = "Eliminar un usuario")
    @DeleteMapping ("/{id}")
    public String deleteUser(@RequestParam int solicitanteId, @PathVariable int id){
        User solicitante = userService.getUserById(solicitanteId);
        return userService.deleteUser(solicitante, id);
    }
    @Operation(summary = "Actualizar un usuario")
    @PutMapping("/{id}")
    public String updateUser(@RequestBody UserActionRequest request,@PathVariable int id){
        return userService.updateUser(request.getSolicitante(),id, request.getUser());
    }
}
