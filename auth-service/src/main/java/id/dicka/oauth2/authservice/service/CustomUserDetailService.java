package id.dicka.oauth2.authservice.service;

import id.dicka.oauth2.authservice.entity.User;
import id.dicka.oauth2.authservice.repository.UserRepsitory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service(value = "userDetailService")
public class CustomUserDetailService implements UserDetailsService {

    private static final Logger log = LoggerFactory.getLogger(CustomUserDetailService.class);

    @Autowired
    private UserRepsitory userRepsitory;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> findUserByUsername = userRepsitory.findByUsername(username);
        if (!findUserByUsername.isPresent()){
            if (log.isInfoEnabled()){
                log.debug("{\"method\" : \"loadUserByUsername\"," +
                        "\"payload\" : \""+username+"\"," +
                        "\"message\" : \"username "+username+" not found\"}");
            }
            throw new BadCredentialsException("Bad Credentials");
        }

        return findUserByUsername.get();
    }
}
