package com.ecommerce.backend.controller;

import com.ecommerce.backend.models.User;
import com.ecommerce.backend.payloads.UserModificationRequest;
import com.ecommerce.backend.payloads.UserRegistrationRequest;
import com.ecommerce.backend.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UsersController {
    private final UserService userService;

    public UsersController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    public List<User> getAllUsers(){
        return userService.getAllUsers();
    }

    @GetMapping("/{userId}")
    public User getUser(
            @PathVariable("userId") Integer userId){
        return userService.getUser(userId);
    }

    @PostMapping()
    public void addNewUser(
            @RequestBody UserRegistrationRequest request){
        userService.addUser(request);
    }

    @DeleteMapping("/{userId}")
    public void removeUser(
            @PathVariable("userId") Integer userId){
        userService.deleteUserById(userId);
    }

    @PutMapping("/{userId}")
    public void updateUser(
            @PathVariable("userId") Integer userId,
            @RequestBody UserModificationRequest request) {
        userService.updateUserById(request, userId);
    }
}
