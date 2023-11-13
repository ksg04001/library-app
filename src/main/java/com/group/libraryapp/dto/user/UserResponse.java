package com.group.libraryapp.dto.user;

import com.group.libraryapp.domain.user.User;
import lombok.Getter;

@Getter
public class UserResponse {
    private Long id;
    private String name;
    private Integer age;


    public UserResponse(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.age = user.getAge();
    }
}
