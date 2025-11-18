package ejb;

import model.LupaPassword;
import org.jboss.logging.Logger;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

@LocalBean
@Stateless
public class LupaPasswordManager {
    @PersistenceContext
    EntityManager em;

    public void persist(LupaPassword lupaPassword){
        try{
            em.persist(lupaPassword);
        }
        catch(PersistenceException ex){
            Logger.getLogger(KrsMahasiswaManager.class).error(ex.getMessage());
        }
    }

    public Boolean confirm(String confirmUrl){
        try{
            Query qry = em.createQuery("select l.confirmUrl from LupaPassword l where l.confirmUrl=?1")
                    .setParameter(1,confirmUrl);
            return !qry.getResultList().isEmpty();
        }
        catch(PersistenceException ex){
            Logger.getLogger(KrsMahasiswaManager.class).error(ex.getMessage());
            return false;
        }
    }

    public Long findUserIdByconfirmUrl(String confirmUrl){
        try{
            Query qry = em.createQuery("select l.User.id from LupaPassword l where l.confirmUrl=?1")
                    .setParameter(1,confirmUrl);
            return (Long)qry.getSingleResult();
        }
        catch(PersistenceException ex){
            Logger.getLogger(KrsMahasiswaManager.class).error(ex.getMessage());
            return null;
        }
    }
}
