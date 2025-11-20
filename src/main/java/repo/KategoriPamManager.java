package repo;


import model.KategoriPam;
import org.jboss.logging.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import java.util.List;

@ApplicationScoped
public class KategoriPamManager {
    @PersistenceContext
    EntityManager em;

    public List<KategoriPam> findAllKategoriPam() {
        try {
            Query qry = em.createQuery("select k from KategoriPam k");
            return qry.getResultList();
        } catch (PersistenceException ex) {
            Logger.getLogger(KategoriPamManager.class).error(ex.getMessage());
            return null;
        }
    }

    public List<String> findAllKelompokKategoriPam(){
        try {
            Query qry = em.createQuery("select k.kelompok from KategoriPam k");
            return qry.getResultList();
        } catch (PersistenceException ex) {
            Logger.getLogger(KategoriPamManager.class).error(ex.getMessage());
            return null;
        }
    }
}
