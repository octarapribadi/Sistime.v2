package ejb;

import com.mysql.cj.log.Log;
import model.Kampus;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Named;
import javax.persistence.*;
import java.util.List;

@Stateless
@LocalBean
public class KampusManager {
    @PersistenceContext
    EntityManager em;

    public List<Kampus> findAllKampus(){
        Query qry = em.createQuery("select k from Kampus k");
        return qry.getResultList();
    }
}
