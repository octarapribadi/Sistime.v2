package repo;

import entity.WaktuKuliah;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

@ApplicationScoped
public class WaktuKuliahManager {
    @PersistenceContext
    EntityManager em;

    public List<WaktuKuliah> findAllWaktuKuliah() {
        TypedQuery<WaktuKuliah> qry = em.createQuery("select w from WaktuKuliah w", WaktuKuliah.class);
        return qry.getResultList();
    }

    public List<WaktuKuliah> findWaktuKuliahByKodeKampus(String kodeKampus) {
        TypedQuery<WaktuKuliah> qry = em
                .createQuery("select w from WaktuKuliah w where w.kampus.kodeKampus = :kodeKampus", WaktuKuliah.class)
                .setParameter("kodeKampus", kodeKampus);
        return qry.getResultList();
    }
}
