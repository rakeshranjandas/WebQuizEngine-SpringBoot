package engine.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class DeleteUnauthorizedException extends RuntimeException {
    public DeleteUnauthorizedException() {
        super("Unauthorized user attempting to delete quiz!");
    }
}
