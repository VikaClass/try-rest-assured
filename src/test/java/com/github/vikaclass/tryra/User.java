package com.github.vikaclass.tryra;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString

public class User {
    private Integer userID;
    private String userName;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String phone;
}
