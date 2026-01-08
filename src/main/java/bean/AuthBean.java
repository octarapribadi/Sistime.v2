package bean;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class AuthBean {
    public boolean auth(String username, String password){
        return true;
    }
}
