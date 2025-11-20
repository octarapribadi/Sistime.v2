package repo;

import model.Agama;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@ApplicationScoped
public class AgamaManager {

    @PersistenceContext
    EntityManager em;

    public List<Agama> findAllAgama(){
        TypedQuery<Agama> qry = em.createQuery("select a from Agama a",Agama.class);
        return qry.getResultList();
    }

    public Agama findAgamaById(String id){
        return em.find(Agama.class, id);
    }

    public void save(Agama agama){
        em.persist(agama);
    }

}
