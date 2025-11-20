package repo;

import model.MataKuliah;
import model.Skedul;
import org.jboss.logging.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.*;
import java.util.List;

@ApplicationScoped
public class MataKuliahManager {
    @PersistenceContext
    EntityManager em;

    public List<MataKuliah> findAllMataKuliah() {
        try {
            TypedQuery<MataKuliah> qry = em.createQuery("select m from MataKuliah m " +
                    "LEFT JOIN FETCH m.idTipematakuliah " +
                    "LEFT JOIN fetch m.kodeProdi", MataKuliah.class);
            return qry.getResultList();
        } catch (PersistenceException ex) {
            Logger.getLogger(MataKuliahManager.class).error(ex.getMessage());
            return null;
        }
    }


    public MataKuliah findMataKuliahByKode(String kode) {
        try {
            Query qry = em.createQuery("select m from MataKuliah m where m.kodeMatakuliah  like ?1").setParameter(1, kode);
            if (!qry.getResultList().isEmpty())
                return (MataKuliah) qry.getResultList().get(0);
                //return new MataKuliah();
            else
                return null;
        } catch (PersistenceException ex) {
            Logger.getLogger(MataKuliahManager.class).error(ex.getMessage());
            return null;
        }
    }

    public MataKuliah findMataKuliahById(Long id) {
        try {
            Query qry = em.createQuery("select m from MataKuliah m where m.id = ?1")
                    .setParameter(1, id);
            return (MataKuliah) qry.getSingleResult();
        } catch (PersistenceException ex) {
            Logger.getLogger(MataKuliahManager.class).error(ex.getMessage());
            return null;
        }
    }

    public MataKuliah findMataKuliahByName(String name) {
        try {
            Query qry = em.createQuery("select m from MataKuliah m where m.namaMatakuliah  like ?1")
                    .setParameter(1, name);
            return (MataKuliah) qry.getSingleResult();
        } catch (PersistenceException ex) {
            Logger.getLogger(MataKuliahManager.class).error(ex.getMessage());
            return null;
        }
    }

    public Integer findSksBySkedulList(List<Skedul> skeduls) {
        try {
            Integer total = 0;
            for (Skedul s : skeduls) {
                TypedQuery<Skedul> qry = em.createQuery("select s from Skedul s join fetch s.idMatakuliah m where s.id=?1", Skedul.class)
                        .setParameter(1, s.getId());
                total += qry.getSingleResult().getIdMatakuliah().getSks();
            }
            return total;
        } catch (PersistenceException ex) {
            Logger.getLogger(MataKuliahManager.class).error(ex.getMessage());
            return null;
        }
    }

    public void persist(MataKuliah mataKuliah) {
        try {
            em.persist(mataKuliah);
        } catch (PersistenceException ex) {
            Logger.getLogger(MataKuliahManager.class).error(ex.getMessage());
            throw new PersistenceException(ex);
        }
    }

    public void remove(MataKuliah mataKuliah) {
        try {
            em.remove(em.merge(mataKuliah));
        } catch (PersistenceException ex) {
            Logger.getLogger(MataKuliahManager.class).error(ex.getMessage());
        }
    }

    public void merge(MataKuliah mataKuliah) {
        try {
            em.merge(mataKuliah);
        } catch (PersistenceException ex) {
            Logger.getLogger(MataKuliahManager.class).error(ex.getMessage());
        }
    }
}