package bookmarks.rest;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by vinayak.j on 2/24/2018.
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
class UserNotFoundException extends RuntimeException {
    UserNotFoundException(String userId) {
        super("could not find user '" + userId + "'." );
    }
}
