package ejb;

import model.TahunAjaran;

import javax.ejb.Stateless;
import javax.enterprise.context.RequestScoped;
import javax.ejb.LocalBean;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import java.util.List;

@Stateless
@LocalBean
public class TahunAjaranManajer {
    @PersistenceContext
    EntityManager em;

    public List<TahunAjaran> findAllTahunAjaran(){
        Query qry = em.createQuery("select t from TahunAjaran t order by t.tahun desc");
        return qry.getResultList();
    }
    public TahunAjaran findTahunAjaranById(Long id){
        try {
            Query qry = em.createQuery("select t from TahunAjaran t where t.id=?1");
            qry.setParameter(1, id);
            return (TahunAjaran)qry.getSingleResult();
        }
        catch(PersistenceException ex){
            return null;
        }
    }
}
