package com.thebinh.identity_service.controller;

import com.thebinh.identity_service.domain.User;
import com.thebinh.identity_service.exception.IdInvalidException;
import com.thebinh.identity_service.exception.UsernameInvalidException;
import com.thebinh.identity_service.service.UserService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {
    private final UserService userService;
    public UserController(UserService userService){
        this.userService = userService;
    }
    //get all user
    @GetMapping("/user")
    public List<User> getAllUser(){
        return this.userService.handleAllUser();
    }

    // update user
    @PutMapping("/user/{id}")
    public String updateUser(@PathVariable("id") long id, @RequestBody User userUpdate){
        User user = this.userService.handleUserById(id);
        this.userService.updateUser(user, userUpdate);
        this.userService.handleSaveUser(user);
        return "đã cập nhật người dùng trong db";
    }


    //create a user
    @PostMapping("/user")
    public String createUser(@Valid @RequestBody User user) throws UsernameInvalidException{
        User checkExitsUser = this.userService.handleUserByUsername(user.getUsername());
        if(checkExitsUser != null){
            throw new UsernameInvalidException("username đã tồn tại");
        }
        this.userService.handleSaveUser(user);
        return "đã tạo người dùng ở trong DB";
    }

    //delete user
    @DeleteMapping("/user/{id}")
    public String deleteUser(@PathVariable("id") long id) throws IdInvalidException {
        if(id >= 1500){
            throw new IdInvalidException("id không được vượt quá 1500");
        }
        User user = this.userService.handleUserById(id);
        if(user == null){
            throw new IdInvalidException("người dùng không tồn tại trong hệ thống");
        }
        this.userService.deleteUserById(id);
        return "đã xóa người dùng trong db";
    }
}
