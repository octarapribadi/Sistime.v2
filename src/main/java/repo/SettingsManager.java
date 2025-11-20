package repo;

import model.Setting;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

@ApplicationScoped
public class SettingsManager {
    @PersistenceContext
    EntityManager em;

    public Setting findSetting(String key){
        Query qry = em.createQuery("select s from Setting s where s.key=?1");
        qry.setParameter(1,key);
        try{
            return (Setting)qry.getSingleResult();
        }
        catch(PersistenceException ex){
            return null;
        }
    }

}
