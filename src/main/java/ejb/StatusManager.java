package ejb;

import model.Agama;
import model.Status;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Stateless
@LocalBean
public class StatusManager {
    Status status;

    @PersistenceContext
    EntityManager em;

    public List<Status> findAllStatus(){
        Query qry = em.createQuery("select s from Status s");
        return qry.getResultList();
    }

    public Status findStatusById(int id){
        Query qry = em.createQuery("select s from Status s where s.idStatus=?1")
                .setParameter(1,id);
        List<Status> list = qry.getResultList();
        if(list.size()>0){
            return list.get(0);
        }
        else
            return null;
    }

}
