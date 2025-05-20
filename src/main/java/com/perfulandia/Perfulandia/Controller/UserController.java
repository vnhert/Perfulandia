package com.perfulandia.Perfulandia.Controller;
import com.perfulandia.Perfulandia.Model.User;
import com.perfulandia.Perfulandia.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class  UserController {
    @Autowired
    UserService userService;

    @GetMapping("/users")
    public String getUsers(){
        return userService.getAllUsers();
    }

    @GetMapping("/users/{id}")
    public String getUserById(@PathVariable int id){
        return userService.getUser(id);
    }
    @PostMapping("/users")
    public String addUser(@RequestBody User user){
        return userService.saveUser(user);
    }

    @DeleteMapping("/users/{id}")
    public String deleteUser(@PathVariable int id){
        return userService.deleteUser(id);
    }

    @PutMapping("/users")
    public String updateUser(@RequestBody User user){
        return userService.updateUser(user);
    }
}
