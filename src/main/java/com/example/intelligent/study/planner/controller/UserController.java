package com.example.intelligent.study.planner.controller;

import com.example.intelligent.study.planner.entity.User;
import com.example.intelligent.study.planner.exceptionHandling.ResourceAlreadyExistException;
import com.example.intelligent.study.planner.exceptionHandling.ResourceNotFoundException;
import com.example.intelligent.study.planner.exceptionHandling.UserEmailExistException;
import com.example.intelligent.study.planner.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }

    @GetMapping
    public List<User> getUsers(){
        return userService.getUsers();
    }

    @PostMapping
    public void registerNewUser(User user) throws UserEmailExistException {
        userService.addNewUser(user);
    }

    @DeleteMapping(path = "{userId}")
    public void deleteUser(@PathVariable Long userId) throws ResourceNotFoundException {
        userService.deleteUser(userId);
    }

    @PutMapping(path = "{userId}")
    public void updateUser(@PathVariable Long userId,
                           @RequestParam(required = false) String email,
                           @RequestParam(required = false) String firstName
                            ) throws ResourceNotFoundException, ResourceAlreadyExistException, UserEmailExistException {
        userService.updateUser(userId, email, firstName);
    }
}
