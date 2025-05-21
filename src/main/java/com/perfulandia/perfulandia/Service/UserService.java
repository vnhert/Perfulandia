package com.perfulandia.perfulandia.Service;
import com.perfulandia.perfulandia.Model.User;
import com.perfulandia.perfulandia.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.Optional;

@Service
public class UserService {


    @Autowired
    private UserRepository userRepository;

    public String getUsers(User solicitante) {
        if (!solicitante.puedeGestionarUsuarios()) {
            return "No tienes permiso para ver usuarios";
        }
        StringBuilder output = new StringBuilder();
        for (User user : userRepository.findAll()) {
            output.append("ID Usuario: ").append(user.getId()).append("\n")
                    .append("Nombre Usuario: ").append(user.getNombre()).append("\n")
                    .append("Correo Usuario: ").append(user.getCorreo()).append("\n")
                    .append("Contraseña: ").append(user.getContraseña()).append("\n\n");
        }
        return output.length() == 0 ? "No se encontraron usuarios" : output.toString();
    }

    public String getUser(User solicitante, int id) {
        if (!solicitante.puedeGestionarUsuarios()) {
            return "No tienes permiso para ver usuarios";
        }
        return userRepository.findById(id)
                .map(user -> "ID Usuario: " + user.getId() + "\n" +
                        "Nombre Usuario: " + user.getNombre() + "\n" +
                        "Correo Usuario: " + user.getCorreo() + "\n" +
                        "Contraseña: " + user.getContraseña())
                .orElse("No se encontró al usuario");
    }

    public String addUser(User solicitante, User user) {
        if (!solicitante.puedeGestionarUsuarios()) {
            return "No tienes permiso para agregar usuarios";
        }
        userRepository.save(user);
        return "Usuario agregado exitosamente";
    }

    public String deleteUser(User solicitante, int id) {
        if (!solicitante.puedeGestionarUsuarios()) {
            return "No tienes permiso para eliminar usuarios";
        }
        Optional<User> userOpt = userRepository.findById(id);
        if (userOpt.isPresent()) {
            userRepository.deleteById(id);
            return "Usuario removido exitosamente";
        }
        return "No se encontró al usuario";
    }
    public String updateUser(User solicitante, int id, User updatedUser) {
        if (!solicitante.puedeGestionarUsuarios()) {
            return "No tienes permiso para actualizar usuarios";
        }
        for (User user : userRepository.findAll()) {
            if (user.getId() == id) {
                user.setNombre(updatedUser.getNombre());
                user.setCorreo(updatedUser.getCorreo());
                user.setContraseña(updatedUser.getContraseña());
                // Actualiza otros campos si es necesario
                userRepository.save(user);
                return "Usuario actualizado exitosamente";
            }
        }
        return "No se encontró al usuario";
    }

    public User getUserById(int id) {
        return userRepository.findById(id).orElse(null);
    }
}
