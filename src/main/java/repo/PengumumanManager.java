package repo;

import entity.Pengumuman;
import org.jboss.logging.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import java.util.List;

@ApplicationScoped
public class PengumumanManager {
    @PersistenceContext
    EntityManager em;

    public List<Pengumuman> findAllPengumumanAktif(){
        Query qry = em.createQuery("select p from Pengumuman p where p.aktif=?1").setParameter(1,true);
        return qry.getResultList();
    }

    public List<Pengumuman> findAllPengumuman(){
        try{
            Query qry = em.createQuery("select p from Pengumuman p");
            return qry.getResultList();
        }
        catch(PersistenceException ex){
            Logger.getLogger(PengumumanManager.class).error(ex.getMessage());
            return null;
        }
    }
}
