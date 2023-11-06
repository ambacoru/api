package ru.ambaco.cmr.service;


import ru.ambaco.cmr.Dto.UserDto;
import ru.ambaco.cmr.entities.User;

public interface AuthenticationService {
    User registration(UserDto userDto);
}
