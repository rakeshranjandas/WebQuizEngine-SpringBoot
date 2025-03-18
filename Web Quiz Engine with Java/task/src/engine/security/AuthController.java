package engine.security;

import engine.requests.RegisterUserRequest;
import engine.services.AppUserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class AuthController {

    @Autowired
    private AppUserService appUserService;

    @PostMapping("/register")
    public void registerUser(@Valid @RequestBody RegisterUserRequest registerUserRequest) {
        appUserService.register(registerUserRequest);
    }
}
