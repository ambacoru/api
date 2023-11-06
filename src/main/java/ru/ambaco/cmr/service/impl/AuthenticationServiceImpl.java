package ru.ambaco.cmr.service.impl;


import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.ambaco.cmr.Dto.UserDto;
import ru.ambaco.cmr.entities.Role;
import ru.ambaco.cmr.entities.User;
import ru.ambaco.cmr.repositories.UserRepository;
import ru.ambaco.cmr.service.AuthenticationService;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public User registration(UserDto userDto){
        User user = new User();
        user.setName(userDto.getName());
        user.setSurname(userDto.getSurname());
        user.setBirthDay(userDto.getBirthDay());
        user.setGender(userDto.getGender());
        user.setRole(Role.USER);
        user.setEmail(userDto.getEmail());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
       return   userRepository.save(user);
    }
}
