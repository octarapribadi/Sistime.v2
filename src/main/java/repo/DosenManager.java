package repo;

import entity.Dosen;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

@ApplicationScoped
public class DosenManager {
    @PersistenceContext
    EntityManager em;

    public List<Dosen> findAllDosen(){
        Query qry = em.createQuery("select d from Dosen d");
        return qry.getResultList();
    }
    public void persist(Dosen dosen){
        em.persist(dosen);
    }
    public void remove(Dosen dosen){
        em.remove(em.merge(dosen));
    }
    public void merge(Dosen dosen){
        em.merge(dosen);
    }
    public List<Dosen> findDosensByNama(String namaDosen){
        Query qry = em.createQuery("select d from Dosen d where d.namaDosen like '%?1%'")
                .setParameter((int)1,namaDosen);
        return qry.getResultList();
    }

    public Dosen findDosenById(Long id){
        TypedQuery<Dosen> qry = em.createQuery("select d from Dosen d where d.id=?1",Dosen.class)
                .setParameter(1,id);
        if(qry.getResultList().size()>0)
            return qry.getResultList().get(0);
        else
            return null;
    }

    public Dosen findDosenByNama(String nama){
        TypedQuery<Dosen> qry = em.createQuery("select d from Dosen d where d.namaDosen like ?1",Dosen.class).setParameter(1,nama);
        if(!qry.getResultList().isEmpty())
            return qry.getResultList().get(0);
        else
            return null;
    }

}
