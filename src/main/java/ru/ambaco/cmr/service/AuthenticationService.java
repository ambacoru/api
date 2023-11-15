package ru.ambaco.cmr.service;


import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.ambaco.cmr.Dto.AuthenticationRequest;
import ru.ambaco.cmr.Dto.AuthenticationResponse;
import ru.ambaco.cmr.Dto.UserDto;
import ru.ambaco.cmr.config.jwt.JwtTokenUtil;
import ru.ambaco.cmr.email.EmailSender;
import ru.ambaco.cmr.entities.ConfirmationToken;
import ru.ambaco.cmr.entities.Role;
import ru.ambaco.cmr.entities.User;
import ru.ambaco.cmr.exception.UserAlreadyExistsException;
import ru.ambaco.cmr.repositories.UserRepository;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenUtil jwtTokenUtil;
    private final AuthenticationManager authenticationManager;
    private final EmailSender emailSender;
    private final ConfirmationTokenService confirmationTokenService;
    private final UserService userService;


    public AuthenticationResponse registration(UserDto userDto){
        Optional<User> userEmail = this.userRepository.findByEmail(userDto.getEmail());

        if(userEmail.isPresent()){

            throw  new UserAlreadyExistsException( "User with email" +userDto.getEmail()+ "already exists");

        }

   User user =  new User();
   user.setName(userDto.getName());
   user.setSurname(userDto.getSurname());
   user.setBirthDay(userDto.getBirthDay());
   user.setGender(userDto.getGender());
   user.setEmail(userDto.getEmail());
   user.setEnabled(false);
   user.setPassword(passwordEncoder.encode(userDto.getPassword()));
   user.setRole(Role.USER);
      userRepository.save(user);
      String token = UUID.randomUUID().toString();
      ConfirmationToken confirmationToken = new ConfirmationToken( token,LocalDateTime.now(),
              LocalDateTime.now().plusMinutes(15), user);

      confirmationTokenService.saveConfirmationToken(confirmationToken);
        emailSender.sendEmail(user);
        return AuthenticationResponse.builder()
                .token(token)
                .build();


    }

    public  AuthenticationResponse authenticate (AuthenticationRequest request){

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(),request.getPassword())
        );
        var user = userRepository.findByEmail(request.getEmail()).orElseThrow();
        var jwtToken = jwtTokenUtil.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    @Transactional
    public String confirmToken(String token) {
        Optional<ConfirmationToken> confirmToken = confirmationTokenService.getToken(token);

        if (confirmToken.isEmpty()) {
            throw new IllegalStateException("Token not found!");
        }

        if (confirmToken.get().getConfirmedAt() != null) {
            throw new IllegalStateException("Email is already confirmed");
        }
        LocalDateTime expiresAt = confirmToken.get().getExpiresAt();
        if (expiresAt.isBefore(LocalDateTime.now())) {
            throw new IllegalStateException("Token is already expired!");
        }

        confirmationTokenService.setConfirmedAt(token);
        userService.enableAppUser(confirmToken.get().getAppUser().getEmail());

        return "Your email is confirmed. Thank you for using our service!";
    }


}
