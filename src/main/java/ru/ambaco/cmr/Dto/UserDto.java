package ru.ambaco.cmr.Dto;


import lombok.Data;

import java.sql.Date;

@Data
public class UserDto {
    private String name;

    private String surname;

    private Date birthDay;

    private String gender;

    private String email;
    private  String password;
}
