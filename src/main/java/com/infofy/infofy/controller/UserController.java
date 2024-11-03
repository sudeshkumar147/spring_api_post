package com.infofy.infofy.controller;

import com.infofy.infofy.dto.ApiResponse;
import com.infofy.infofy.model.User;
import com.infofy.infofy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<User>>> getAllUsers() {
        List<User> userList =  userService.getAllUsers();
        ApiResponse<List<User>> response = new ApiResponse<>(true, "User List", userList);
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<ApiResponse<User>> saveUser(@RequestBody User user) {
        User savedUser = userService.saveUser(user);
        ApiResponse<User> response = new ApiResponse<>(true, "User created", savedUser);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Optional<User>>> getUserById(@PathVariable Long id) {
        Optional<User> singleUser = userService.getUserById(id);

        ApiResponse<Optional<User>> response = new ApiResponse<>(true, "User fetched", singleUser);

        return  ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<String>> deleteUser(@PathVariable Long id) {
        userService.deleteUserBid(id);
        ApiResponse<String> response = new ApiResponse<>(true, "user Deleted", null);
        return ResponseEntity.ok(response);
    }
}
