package ejb;

import model.Dokumen;
import model.JenisDokumen;
import org.jboss.logging.Logger;
import org.jboss.logging.Logger.Level;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import java.util.List;

@LocalBean
@Stateless
public class DokumenManager {

    @PersistenceContext
    EntityManager em;

    public List<Dokumen> findDokumenByUserId(long idUser){
        Query qry = em.createQuery("select d from Dokumen d where d.idUser=?1");
        qry.setParameter(1,idUser);
        try{
            return qry.getResultList();
        }
        catch(PersistenceException ex){
            Logger.getLogger(DokumenManager.class).log(Level.ERROR,ex);
            return null;
        }
    }
    public List<JenisDokumen> findAllJenisDokumen(){
        Query qry = em.createQuery("select j from JenisDokumen j");
        try{
            return qry.getResultList();
        }
        catch(PersistenceException ex){
            Logger.getLogger(DokumenManager.class).log(Level.ERROR,ex);
            return null;
        }
    }

    public void persist(Dokumen dokumen){
        try{
            em.persist(dokumen);
        }
        catch(PersistenceException ex){
            Logger.getLogger(DokumenManager.class).log(Level.ERROR,ex);
        }
    }

    public void merge(Dokumen dokumen){
        try{
            em.merge(dokumen);
        }
        catch(PersistenceException ex){
            Logger.getLogger(DokumenManager.class).log(Level.ERROR,ex);
        }
    }

    public void delete(Dokumen dokumen){
        try{
            em.remove(em.merge(dokumen));
        }
        catch(PersistenceException ex){
            Logger.getLogger(DokumenManager.class).log(Level.ERROR,ex);
        }
    }
}
