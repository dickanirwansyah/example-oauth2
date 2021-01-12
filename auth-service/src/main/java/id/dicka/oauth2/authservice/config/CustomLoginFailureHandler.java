package id.dicka.oauth2.authservice.config;

import id.dicka.oauth2.authservice.constant.AuthConstant;
import id.dicka.oauth2.authservice.entity.User;
import id.dicka.oauth2.authservice.repository.UserRepsitory;
import id.dicka.oauth2.authservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@Component
public class CustomLoginFailureHandler extends SimpleUrlAuthenticationFailureHandler {

    @Autowired
    private UserService userService;

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
                                        AuthenticationException authException) throws IOException, ServletException{

        String parameterUsername = request.getParameter("username");
        System.out.println("PAYLOAD REQUEST => "+parameterUsername);
        User findUserByUsername = userService.findUserByUsername(parameterUsername);

        if (findUserByUsername != null){
            if (findUserByUsername.isEnabled() && findUserByUsername.isAccountNonLocked()){
                if (findUserByUsername.getFailedAttemps() < AuthConstant.MAX_FAILED_ATTEMPS - 1){
                    userService.incrementFailedAttemps(findUserByUsername);
                }else{
                    userService.lockedAccountUser(findUserByUsername);
                    authException = new LockedException("Maaf account anda sudah 3 kali salah password, " +
                            "harap tunggu 24 jam kemudian untuk access account anda kembali");
                }
            }else if (!findUserByUsername.isAccountNonLocked()){
                if (userService.unlockWhenTimeExpired(findUserByUsername)){
                    authException = new LockedException("Account anda telah terbuka. harap login kembali");
                }
            }
        }

        super.onAuthenticationFailure(request, response, authException);
    }

}
