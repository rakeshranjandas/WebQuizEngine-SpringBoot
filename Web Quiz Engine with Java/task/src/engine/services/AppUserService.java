package engine.services;

import engine.entities.AppUser;
import engine.exceptions.AppUserAlreadyExistsException;
import engine.repositories.AppUserRepository;
import engine.requests.RegisterUserRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AppUserService {

    @Autowired
    private AppUserRepository appUserRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public boolean register(RegisterUserRequest registerUserRequest) {

        Optional<AppUser> existingAppUser = appUserRepository.findAppUserByUsername(registerUserRequest.getEmail());

        if (!existingAppUser.isEmpty()) {
            throw new AppUserAlreadyExistsException();
        }

        AppUser newAppUser = new AppUser();
        newAppUser.setUsername(registerUserRequest.getEmail());
        newAppUser.setPassword(passwordEncoder.encode(registerUserRequest.getPassword()));

        appUserRepository.save(newAppUser);

        return true;
    }
}
