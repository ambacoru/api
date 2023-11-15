package ru.ambaco.cmr.controllers;


import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.ambaco.cmr.Dto.AuthenticationRequest;
import ru.ambaco.cmr.Dto.AuthenticationResponse;
import ru.ambaco.cmr.Dto.UserDto;
import ru.ambaco.cmr.service.AuthenticationService;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> registration(@RequestBody UserDto userDto){
        return ResponseEntity.ok(authenticationService.registration(userDto));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(
            @RequestBody AuthenticationRequest request
    ) {
        return ResponseEntity.ok(authenticationService.authenticate(request));
    }

    @GetMapping(path = "/confirm")
    public ResponseEntity<String> confirm(@RequestParam("token") String token){
        return ResponseEntity.ok(authenticationService.confirmToken(token));
    }

}
