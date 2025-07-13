package com.perfulandia.perfulandia.Service;

import com.perfulandia.perfulandia.Model.User;
import com.perfulandia.perfulandia.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    /**
     * Obtiene todos los usuarios si el solicitante tiene permisos.
     *
     * @param solicitante Usuario que realiza la solicitud.
     * @return Lista de usuarios.
     * @throws UnauthorizedException Si el solicitante no tiene permisos.
     */
    public List<User> getUsers(User solicitante) {
        if (!solicitante.puedeGestionarUsuarios()) {
            throw new UnauthorizedException("No tienes permiso para ver usuarios");
        }
        return userRepository.findAll();
    }

    /**
     * Obtiene un usuario por su ID si el solicitante tiene permisos.
     *
     * @param solicitante Usuario que realiza la solicitud.
     * @param id ID del usuario a buscar.
     * @return Usuario encontrado.
     * @throws UnauthorizedException Si el solicitante no tiene permisos.
     * @throws UserNotFoundException Si el usuario no existe.
     */
    public User getUser(User solicitante, int id) {
        if (!solicitante.puedeGestionarUsuarios()) {
            throw new UnauthorizedException("No tienes permiso para ver usuarios");
        }
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("No se encontró al usuario con ID: " + id));
    }

    /**
     * Agrega un nuevo usuario si el solicitante tiene permisos.
     *
     * @param solicitante Usuario que realiza la solicitud.
     * @param user Usuario a agregar.
     * @return Usuario agregado.
     * @throws UnauthorizedException Si el solicitante no tiene permisos.
     */
    public User addUser(User solicitante, User user) {
        if (!solicitante.puedeGestionarUsuarios()) {
            throw new UnauthorizedException("No tienes permiso para agregar usuarios");
        }
        return userRepository.save(user);
    }

    /**
     * Elimina un usuario por su ID si el solicitante tiene permisos.
     *
     * @param solicitante Usuario que realiza la solicitud.
     * @param id ID del usuario a eliminar.
     * @throws UnauthorizedException Si el solicitante no tiene permisos.
     * @throws UserNotFoundException Si el usuario no existe.
     */
    public void deleteUser(User solicitante, int id) {
        if (!solicitante.puedeGestionarUsuarios()) {
            throw new UnauthorizedException("No tienes permiso para eliminar usuarios");
        }
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("No se encontró al usuario con ID: " + id));
        userRepository.delete(user);
    }

    /**
     * Actualiza un usuario existente si el solicitante tiene permisos.
     *
     * @param solicitante Usuario que realiza la solicitud.
     * @param id ID del usuario a actualizar.
     * @param updatedUser Datos actualizados del usuario.
     * @return Usuario actualizado.
     * @throws UnauthorizedException Si el solicitante no tiene permisos.
     * @throws UserNotFoundException Si el usuario no existe.
     */
    public User updateUser(User solicitante, int id, User updatedUser) {
        if (!solicitante.puedeGestionarUsuarios()) {
            throw new UnauthorizedException("No tienes permiso para actualizar usuarios");
        }
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("No se encontró al usuario con ID: " + id));
        user.setNombre(updatedUser.getNombre());
        user.setCorreo(updatedUser.getCorreo());
        user.setContraseña(updatedUser.getContraseña());
        // Actualiza otros campos si es necesario
        return userRepository.save(user);
    }

    /**
     * Obtiene un usuario por su ID sin validación de permisos.
     *
     * @param id ID del usuario a buscar.
     * @return Usuario encontrado o null si no existe.
     */
    public User getUserById(int id) {
        return userRepository.findById(id).orElse(null);
    }
}