package com.perfulandia.Perfulandia.Repository;
import com.perfulandia.Perfulandia.Model.User;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Repository
public class UserRepository {
    private List<User> users = new ArrayList<>();

    public UserRepository() {

    }

    public String getUsers() {
        String output = "";
        for (User user : users) {
            output += "ID Usuario: " + user.getId() + "\n";
            output += "Nombre Usuario: " + user.getNombre() + "\n";
            output += "Rol: " + user.getRol() + "\n";
            output += "Correo Usuario: " + user.getCorreo() + "\n";
            output += "Contraseña: " + user.getContraseña() + "\n\n";
        }
        if (output.isEmpty()) {
            return "No se encontraron usuarios";
        } else {
            return output;
        }
    }

    public String getUser(int id) {
        for (User user : users) {
            if (Objects.equals(user.getId(), id)) {
                return "ID Usuario: " + user.getId() + "\n" +
                        "Nombre Usuario: " + user.getNombre() + "\n" +
                        "Rol: " + user.getRol() + "\n" +
                        "Correo Usuario: " + user.getCorreo() + "\n" +
                        "Contraseña: " + user.getContraseña();
            }
        }
        return "No se encontró al usuario";
    }

    public String addUser(User user) {
        users.add(user);
        return "Usuario agregado exitosamente";
    }

    public String deleteUser(int id) {
        for (User user : users) {
            if (Objects.equals(user.getId(), id)) {
                users.remove(user);
                return "Usuario removido exitosamente";
            }
        }
        return "No se encontró al usuario";
    }

    public String updateUser(User updatedUser) {
        for (int i = 0; i < users.size(); i++) {
            if (Objects.equals(users.get(i).getId(), updatedUser.getId())) {
                users.set(i, updatedUser);
                return "Usuario actualizado exitosamente";
            }
        }
        return "No se encontró al usuario";
    }
}
