package bookmarks.security;

/**
 * Created by vinayak.j on 2/24/2018.
 */
class UserNotFoundException extends RuntimeException {
    UserNotFoundException(String userId) {
        super("could not find user '" + userId + "'." );
    }
}
