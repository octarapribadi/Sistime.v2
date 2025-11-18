package ejb;

import model.WaktuKuliah;

import javax.ejb.Local;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Stateless
@LocalBean
public class WaktuKuliahManager {
    @PersistenceContext
    EntityManager em;

    public List<WaktuKuliah> findAllWaktuKuliah(){
        Query qry = em.createQuery("select w from WaktuKuliah w");
        return qry.getResultList();
    }

    public List<WaktuKuliah> findWaktuKuliahByKodeKampus(String kodeKampus){
        Query qry = em.createQuery("select w from WaktuKuliah w where w.kampus.kodeKampus = ?1")
                .setParameter(1,kodeKampus);
        return qry.getResultList();
    }
}
