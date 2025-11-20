package repo;

import entity.Pam;
import org.jboss.logging.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import java.util.List;

@ApplicationScoped
public class PamManager {
    @PersistenceContext
    EntityManager em;

    public List<Pam> findAllPamByUserId(Long userId) {
        try {
            Query qry = em.createQuery("select p from Pam p " +
                            "join fetch p.kategoriPam k " +
                            "where p.user.id = ?1")
                    .setParameter(1, userId);
            return qry.getResultList();
        } catch (PersistenceException ex) {
            Logger.getLogger(PamManager.class).error(ex.getMessage());
            return null;
        }
    }

    public List<Pam> findAllPam(){
        try {
            Query qry = em.createQuery("select p from Pam p " +
                    "join fetch p.kategoriPam k " +
                    "join fetch p.user u " +
                    "join fetch u.mahasiswa m " +
                    "join fetch u.statusMahasiswa s " +
                    "join fetch s.kelas kls");
            return qry.getResultList();
        } catch (PersistenceException ex) {
            Logger.getLogger(PamManager.class).error(ex.getMessage());
            return null;
        }
    }

    public void persist(Pam pam){
        try{
            em.persist(pam);
        }
        catch (PersistenceException ex) {
            Logger.getLogger(PamManager.class).error(ex.getMessage());
        }
    }

    public void merge(Pam pam){
        try{
            em.merge(pam);
        }
        catch (PersistenceException ex) {
            Logger.getLogger(PamManager.class).error(ex.getMessage());
        }
    }

    public void remove(Pam pam){
        try{
            em.remove(em.merge(pam));
        }
        catch (PersistenceException ex) {
            Logger.getLogger(PamManager.class).error(ex.getMessage());
        }
    }
}
