package com.perfulandia.Perfulandia.Service;
import com.perfulandia.Perfulandia.Model.User;
import com.perfulandia.Perfulandia.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public String saveUser(User user) {
        return userRepository.addUser(user);
    }

    public String getUser(int id) {return userRepository.getUser(id);
    }
    public String deleteUser(int id) {
        return userRepository.deleteUser(id);
    }
    public String getAllUsers() {
        return userRepository.getUsers();
    }
    public String updateUser(User user) {
        return userRepository.updateUser(user);
    }
}
