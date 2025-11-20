package repo;

import model.TipeMataKuliah;
import org.jboss.logging.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.*;
import java.util.List;

@ApplicationScoped
public class TipeMataKuliahManager {

    @PersistenceContext
    EntityManager em;

    public List<TipeMataKuliah> findAllTipeMataKuliah(){
        try {
            TypedQuery<TipeMataKuliah> qry = em.createQuery("select t from TipeMataKuliah t", TipeMataKuliah.class);
            return qry.getResultList();
        }
        catch(PersistenceException ex){
            Logger.getLogger(TipeMataKuliah.class).error("error:" + ex.getMessage());
            return null;
        }
    }

    public TipeMataKuliah findTipeMatakuliahById(Long id){
        Query qry = em.createQuery("select t from TipeMataKuliah t where t.id=?1")
                .setParameter(1,id);
        return (TipeMataKuliah) qry.getResultList().get(0);
    }

    public void merge(TipeMataKuliah tipeMataKuliah){
        em.merge(tipeMataKuliah);
    }

}