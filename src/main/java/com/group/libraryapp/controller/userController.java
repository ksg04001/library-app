package com.group.libraryapp.controller;

import com.group.libraryapp.dto.user.UserResponse;
import com.group.libraryapp.dto.user.UserSaveRequest;
import com.group.libraryapp.dto.user.UserUpdateRequest;
import com.group.libraryapp.service.user.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class userController {

    private final UserService userService;

    public userController(UserService userService) {
        this.userService = userService;
    }

    // 유저 정보 등록
    @PostMapping ("/user")
    public void saveUser(@RequestBody UserSaveRequest request){
        userService.saveUser(request);
    }

    //유저 리스트
    @GetMapping("/user")
    public List<UserResponse> showUser(){

        return userService.getUsers();
    }

    @PutMapping("/user")
    public void updateUser(@RequestBody UserUpdateRequest request){

        userService.updateUser(request);

    }

    @DeleteMapping("/user")
    public void deleteUser(@RequestParam String name){

        userService.deleteUser(name);
    }



}
