package id.dicka.oauth2.authservice.service.impl;

import id.dicka.oauth2.authservice.constant.AuthConstant;
import id.dicka.oauth2.authservice.entity.User;
import id.dicka.oauth2.authservice.repository.UserRepsitory;
import id.dicka.oauth2.authservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepsitory userRepsitory;

    @Override
    public User findUserByUsername(String username) {
        Optional<User> findUser = userRepsitory.findByUsername(username);
        return findUser.get();
    }

    @Override
    public void incrementFailedAttemps(User user) {
        int newFailedAttemps = user.getFailedAttemps() + 1;
        userRepsitory.updateUpdateFailedAttemps(newFailedAttemps, user.getUsername());
    }

    @Override
    public void resetFailedAttemps(String username) {
        userRepsitory.updateUpdateFailedAttemps(0, username);
    }

    @Override
    public void lockedAccountUser(User user) {
        user.setAccountLocked(false);
        user.setLockTime(new Date());
        userRepsitory.save(user);
    }

    @Override
    public boolean unlockWhenTimeExpired(User user) {
        long lockTimeInMillis = user.getLockTime().getTime();
        long currentTimeInMillis = System.currentTimeMillis();

        if (lockTimeInMillis + AuthConstant.LOCK_TIME_DURATIONS < currentTimeInMillis){
            user.setAccountLocked(true);
            user.setLockTime(null);
            user.setFailedAttemps(0);
            userRepsitory.save(user);
            return true;
        }
        return false;
    }
}
