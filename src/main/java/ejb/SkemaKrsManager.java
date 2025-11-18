package ejb;

import model.SkemaKrs;
import model.TahunAjaran;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import java.util.List;

@Stateless
@LocalBean
public class SkemaKrsManager {
    @PersistenceContext
    EntityManager em;

    public List<SkemaKrs> findAllSkemaKrs(){
        Query qry = em.createQuery("select s from SkemaKrs s join fetch s.tahunAjaran t");
        return qry.getResultList();
    }

    public void persist(SkemaKrs skemaKrs){
        em.persist(skemaKrs);
    }

    public void nonAktifkanSemuaSkemaKrs(){
        Query qry = em.createQuery("update SkemaKrs s set s.aktif=0");
        qry.executeUpdate();
    }

    public void hapus(SkemaKrs skemaKrs){

        em.remove(em.merge(skemaKrs));
    }

    public void aktifkan(SkemaKrs skemaKrs){
        Query qry = em.createQuery("update SkemaKrs s set s.aktif=1 where s.id=?1")
                .setParameter(1,skemaKrs.getId());
        qry.executeUpdate();
    }

    public void nonAktifkan(SkemaKrs skemaKrs){
        Query qry = em.createQuery("update SkemaKrs s set s.aktif=0 where s.id=?1")
                .setParameter(1,skemaKrs.getId());
        qry.executeUpdate();
    }

    public SkemaKrs findSkemaKrsByAktif(){
        Query qry = em.createQuery("select s from SkemaKrs s join fetch s.tahunAjaran t where s.aktif=true");
        try{
            return (SkemaKrs)qry.getSingleResult();
        }
        catch(PersistenceException ex){
            return null;
        }
    }

}
