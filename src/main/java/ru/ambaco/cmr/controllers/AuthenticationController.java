package ru.ambaco.cmr.controllers;


import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.ambaco.cmr.Dto.UserDto;
import ru.ambaco.cmr.entities.User;
import ru.ambaco.cmr.service.AuthenticationService;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/registration")
    public ResponseEntity<User> registration(@RequestBody UserDto userDto){
        return ResponseEntity.ok(authenticationService.registration(userDto));
    }

}
