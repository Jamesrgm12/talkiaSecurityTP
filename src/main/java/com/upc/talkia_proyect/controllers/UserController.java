package com.upc.talkia_proyect.controllers;

import com.upc.talkia_proyect.dtos.UserDTO;
import com.upc.talkia_proyect.dtos.queries.ShowSuscriptionDetailsDTO;
import com.upc.talkia_proyect.entities.Role;
import com.upc.talkia_proyect.entities.User;
import com.upc.talkia_proyect.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    ModelMapper modelMapper = new ModelMapper();

    @DeleteMapping("/user/{id}")
    @PreAuthorize("hasRole('USER')")
    public void deleteUser(@PathVariable int id){
        userService.deleteUser(id);
    }

    @PostMapping("/user")
    public UserDTO insertUser(@RequestBody UserDTO userDTO){
        ModelMapper modelmapper = new ModelMapper();
        User user = modelmapper.map(userDTO, User.class);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user = userService.insertUser(user);
        return modelmapper.map(user, UserDTO.class);
    }

    @GetMapping("/users_exist/{userName}/{password}")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public boolean existsUser(@PathVariable String userName, @PathVariable String password){
        return userService.existsUser(userName, password);
    }

    @PutMapping("/user")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public UserDTO updateUser(@RequestBody UserDTO userDTO){
        ModelMapper modelmapper = new ModelMapper();
        User user = modelmapper.map(userDTO, User.class);
        user = userService.updateUser(user);
        return modelmapper.map(user, UserDTO.class);
    }

    @GetMapping("/users")
    @PreAuthorize("hasRole('ADMIN')")
    public List<UserDTO> listUsers(){
        List<User>users = userService.listUsers();
        List<UserDTO> userDTOS = modelMapper.map(users, List.class);
        return userDTOS;
    }

    @GetMapping("/users_register_date/{startDate}/{endDate}")
    @PreAuthorize("hasRole('ADMIN')")
    public List<User> listUserByRegisterDate(@PathVariable LocalDate startDate,@PathVariable  LocalDate endDate){
        return userService.listUserByRegisterDate(startDate, endDate);
    }

    @GetMapping("/users_status/{status}")
    @PreAuthorize("hasRole('ADMIN')")
    public List<User> listUsersByStatus(@PathVariable String status){
        return userService.listUsersByStatus(status);
    }

    @GetMapping("/user_by_username/{username}")
    @PreAuthorize("hasRole('ADMIN')")
    public  List<User> getUserByUserNameContains(@PathVariable String username){
        return  userService.getUserByUserNameContains(username);
    }
    @GetMapping("/user_by_id/{userId}")
    @PreAuthorize("hasRole('ADMIN')")
    public User getUserById(@PathVariable int userId){
        return userService.getUserById(userId);
    }

    @GetMapping("/current_suscription_by_user_id/{userId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ShowSuscriptionDetailsDTO getCurrentSuscription(@PathVariable int userId){
        return userService.getCurrentSuscription(userId);
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/userView")
    public String userEndpoint() {
        return "This is the user endpoint, accessible to users with USER or ADMIN role.";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/adminView")
    public String adminEndpoint() {
        return "This is the admin endpoint, accessible only to users with ADMIN role.";
    }
}
