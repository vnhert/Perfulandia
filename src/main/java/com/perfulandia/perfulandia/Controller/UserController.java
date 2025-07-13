package com.perfulandia.perfulandia.Controller;

import com.perfulandia.perfulandia.Model.User;
import com.perfulandia.perfulandia.Model.UserActionRequest;
import com.perfulandia.perfulandia.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.parameters.Parameter;
import io.swagger.v3.oas.annotations.parameters.RequestBody;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Tag(name = "Usuarios", description = "Operaciones relacionadas con los usuarios")
@RestController
@RequestMapping("/usuarios")
public class UserController {

    @Autowired
    private UserService userService;

    @Operation(summary = "Obtener todos los usuarios", description = "Devuelve una lista de todos los usuarios registrados.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Lista obtenida con éxito"),
            @ApiResponse(responseCode = "403", description = "No autorizado")
    })
    @GetMapping
    public CollectionModel<EntityModel<User>> getUsers(
            @RequestBody(description = "Usuario solicitante", required = true) User solicitante) {
        List<EntityModel<User>> users = userService.getUsers(solicitante).stream()
                .map(user -> EntityModel.of(user,
                        linkTo(methodOn(UserController.class).getUserById(solicitante, user.getId())).withSelfRel(),
                        linkTo(methodOn(UserController.class).getUsers(solicitante)).withRel("usuarios")))
                .collect(Collectors.toList());

        return CollectionModel.of(users,
                linkTo(methodOn(UserController.class).getUsers(solicitante)).withSelfRel());
    }

    @Operation(summary = "Obtener un usuario por ID", description = "Devuelve los detalles de un usuario específico.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Usuario obtenido con éxito"),
            @ApiResponse(responseCode = "403", description = "No autorizado"),
            @ApiResponse(responseCode = "404", description = "Usuario no encontrado")
    })
    @GetMapping("/{id}")
    public EntityModel<User> getUserById(
            @RequestBody(description = "Usuario solicitante", required = true) User solicitante,
            @Parameter(description = "ID del usuario a buscar", required = true) @PathVariable int id) {
        User user = userService.getUser(solicitante, id);
        return EntityModel.of(user,
                linkTo(methodOn(UserController.class).getUserById(solicitante, id)).withSelfRel(),
                linkTo(methodOn(UserController.class).getUsers(solicitante)).withRel("usuarios"));
    }

    @Operation(summary = "Registrar un nuevo usuario", description = "Registra un nuevo usuario en el sistema.")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Usuario creado con éxito"),
            @ApiResponse(responseCode = "400", description = "Solicitud inválida")
    })
    @PostMapping
    public EntityModel<User> addUser(
            @RequestBody(description = "Datos del usuario a registrar", required = true) UserActionRequest request) {
        User newUser = userService.addUser(request.getSolicitante(), request.getUser());
        return EntityModel.of(newUser,
                linkTo(methodOn(UserController.class).getUserById(request.getSolicitante(), newUser.getId())).withSelfRel(),
                linkTo(methodOn(UserController.class).getUsers(request.getSolicitante())).withRel("usuarios"));
    }

    @Operation(summary = "Eliminar un usuario", description = "Elimina un usuario específico del sistema.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Usuario eliminado con éxito"),
            @ApiResponse(responseCode = "403", description = "No autorizado"),
            @ApiResponse(responseCode = "404", description = "Usuario no encontrado")
    })
    @DeleteMapping("/{id}")
    public String deleteUser(
            @Parameter(description = "ID del solicitante", required = true) @RequestParam int solicitanteId,
            @Parameter(description = "ID del usuario a eliminar", required = true) @PathVariable int id) {
        User solicitante = userService.getUserById(solicitanteId);
        return userService.deleteUser(solicitante, id);
    }

    @Operation(summary = "Actualizar un usuario", description = "Actualiza los detalles de un usuario existente.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Usuario actualizado con éxito"),
            @ApiResponse(responseCode = "403", description = "No autorizado"),
            @ApiResponse(responseCode = "404", description = "Usuario no encontrado")
    })
    @PutMapping("/{id}")
    public EntityModel<User> updateUser(
            @RequestBody(description = "Datos del usuario a actualizar", required = true) UserActionRequest request,
            @Parameter(description = "ID del usuario a actualizar", required = true) @PathVariable int id) {
        User updatedUser = userService.updateUser(request.getSolicitante(), id, request.getUser());
        return EntityModel.of(updatedUser,
                linkTo(methodOn(UserController.class).getUserById(request.getSolicitante(), id)).withSelfRel(),
                linkTo(methodOn(UserController.class).getUsers(request.getSolicitante())).withRel("usuarios"));
    }
}