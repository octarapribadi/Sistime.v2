package bean;

import dto.PasswordDto;
import entity.User;
import org.eclipse.microprofile.jwt.JsonWebToken;
import org.mindrot.jbcrypt.BCrypt;
import repo.UserManager;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;

@RequestScoped
public class PasswordBean {

    @Inject
    UserManager userManager;

    @Inject
    JsonWebToken jsonWebToken;

    @Transactional
    public void forgotPassword(PasswordDto dto){
        String password = dto.getPassword();
        User user = userManager.findUserByUserId(Long.parseLong(jsonWebToken.getSubject()));
        String hashPass = BCrypt.hashpw(password, BCrypt.gensalt(5));
        user.setPassword(hashPass);
        userManager.persist(user);
    }
}
