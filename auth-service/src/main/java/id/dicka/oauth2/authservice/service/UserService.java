package id.dicka.oauth2.authservice.service;

import id.dicka.oauth2.authservice.entity.User;

public interface UserService {

    User findUserByUsername(String username);
    void incrementFailedAttemps(User user);
    void resetFailedAttemps(String username);
    void lockedAccountUser(User user);
    boolean unlockWhenTimeExpired(User user);

}
