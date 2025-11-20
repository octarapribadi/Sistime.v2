package repo;

import model.Kampus;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@ApplicationScoped
public class KampusManager {
    @PersistenceContext
    EntityManager em;

    public List<Kampus> findAllKampus(){
        Query qry = em.createQuery("select k from Kampus k");
        return qry.getResultList();
    }
}
