package engine.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class AppUserAlreadyExistsException extends RuntimeException {
    public AppUserAlreadyExistsException() {
        super("This user already exists in the system!");
    }

}
