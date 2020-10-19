package ru.innopolis.stc27.maslakov.enterprise.project42.controllers.user;

import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.innopolis.stc27.maslakov.enterprise.project42.dto.CredentialsDTO;
import ru.innopolis.stc27.maslakov.enterprise.project42.dto.ErrorMessageDTO;
import ru.innopolis.stc27.maslakov.enterprise.project42.dto.SignupDTO;
import ru.innopolis.stc27.maslakov.enterprise.project42.entities.users.User;
import ru.innopolis.stc27.maslakov.enterprise.project42.services.api.RegisterService;

import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
public class RegisterController {

    private final RegisterService registerService;

    @ResponseBody
    @PostMapping(value = "/register", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity register(@RequestBody CredentialsDTO credentials) {
        Optional<User> user = registerService.signup(SignupDTO.builder()
                .login(credentials.getLogin())
                .password(credentials.getPassword())
                .build());
        return user.isPresent() ?
                ResponseEntity.ok().build() : ResponseEntity.badRequest()
                .body(new ErrorMessageDTO("Пользователь с таким именем уже существует."));
    }

    @PostMapping(value = "/staff/register", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity staffRegister(@RequestBody SignupDTO data) {
        Optional<User> user = registerService.signup(data);
        return user.isPresent() ? ResponseEntity.ok().build() : ResponseEntity.badRequest().build();
    }

}
