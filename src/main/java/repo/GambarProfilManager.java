package repo;

import model.GambarProfil;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

@ApplicationScoped
public class GambarProfilManager {
    @PersistenceContext
    EntityManager em;

    public void persist(GambarProfil gambarProfil){
        em.persist(gambarProfil);
    }

    public void merge(GambarProfil gambarProfil){
        em.merge(gambarProfil);
    }

    public GambarProfil findGambarProfileById(Long idUser){
        try {
            Query qry = em.createQuery("select g from GambarProfil g where g.idUser=?1");
            qry.setParameter(1, idUser);
            return (GambarProfil) qry.getSingleResult();
        }
        catch(PersistenceException ex){
            return null;
        }
    }
}
