package ejb;

import model.Agama;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Stateless
@LocalBean
public class AgamaManager {

    Agama agama;
    @PersistenceContext
    EntityManager em;

    public List<Agama> findAllAgama(){
        Query qry = em.createQuery("select a from Agama a");
        return qry.getResultList();
    }

    public Agama findAgamaById(String id){
        //Agama agama = new Agama();
        Query qry = em.createQuery("select a from Agama a where a.idAgama = ?1").setParameter(1,id);
        List<Agama> list = qry.getResultList();
        if(list.size()>0)
            return list.get(0);
        else
            return null;
    }
    public void save(Agama agama){
        em.persist(agama);
    }

}
