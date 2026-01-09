package bean;

import entity.User;
import org.mindrot.jbcrypt.BCrypt;
import repo.UserManager;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class AuthBean {
    @Inject
    UserManager userManager;
    public boolean auth(String username, String password){
        User user = userManager.findUserByUsername(username);
        return BCrypt.checkpw(password, user.getPassword());
    }
}
