package com.perfulandia.perfulandia.Controller;
import com.perfulandia.perfulandia.Model.User;
import com.perfulandia.perfulandia.Model.UserActionRequest;
import com.perfulandia.perfulandia.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuarios")
public class  UserController {
    @Autowired
    UserService userService;

    @GetMapping ()
    public String getUsers(@RequestBody User solicitante){
        return userService.getUsers(solicitante);
    }

    @GetMapping("/{id}")
    public String getUserById(@RequestBody User solicitante, @PathVariable int id){
        return userService.getUser(solicitante, id);
    }

    @PostMapping()
    public String addUser(@RequestBody UserActionRequest request){
        return userService.addUser(request.getSolicitante(), request.getUser());
    }

    @DeleteMapping ("/{id}")
    public String deleteUser(@RequestParam int solicitanteId, @PathVariable int id){
        User solicitante = userService.getUserById(solicitanteId);
        return userService.deleteUser(solicitante, id);
    }

    @PutMapping("/{id}")
    public String updateUser(@RequestBody UserActionRequest request,@PathVariable int id){
        return userService.updateUser(request.getSolicitante(),id, request.getUser());
    }
}
