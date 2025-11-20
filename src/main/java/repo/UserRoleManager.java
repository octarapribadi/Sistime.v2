package repo;

import model.UserRole;
import org.jboss.logging.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;

@ApplicationScoped
public class UserRoleManager {
    @PersistenceContext
    EntityManager em;

    public void persist(UserRole userRole){
        try{
            em.persist(userRole);
        }
        catch(PersistenceException ex){
            Logger.getLogger(RegistrasiManager.class).log(Logger.Level.ERROR,ex.getMessage());
        }
    }
}
