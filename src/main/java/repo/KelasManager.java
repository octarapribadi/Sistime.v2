package repo;

import entity.Kelas;
import org.jboss.logging.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.*;
import java.util.List;

@ApplicationScoped
public class KelasManager {
    @PersistenceContext
    EntityManager em;

    public List<Kelas> findAllKelas() {
        try {
            Query qry = em.createQuery("select k from Kelas k " +
                    "JOIN FETCH k.programStudi " +
                    "LEFT JOIN FETCH k.idDosenwali d");
            return qry.getResultList();
        } catch (PersistenceException ex) {
            Logger.getLogger(NilaiManager.class).error(ex.getMessage());
            return null;
        }
    }

    public Kelas findKelasByKodeKelas(String kodeKelas) {
        try {
            Query qry = em.createQuery("select k from Kelas k where k.kodeKelas=?1").setParameter(1, kodeKelas);
            return (Kelas) qry.getSingleResult();
        } catch (PersistenceException ex) {
            Logger.getLogger(NilaiManager.class).error(ex.getMessage());
            return null;
        }
    }

    public Kelas findKelasByIdUser(long idUser) {
        try {
            TypedQuery<Kelas> query = em.createQuery("select k from Kelas k join fetch k.statusMahasiswa s where s.user.id=?1", Kelas.class)
                    .setParameter(1, idUser);
            return query.getSingleResult();
        } catch (PersistenceException ex) {
            Logger.getLogger(NilaiManager.class).error(ex.getMessage());
            return null;
        }
    }

    public Kelas findKelasByNamaKelas(String namaKelas) {
        try {
            TypedQuery<Kelas> qry = em.createQuery("select k from Kelas k where k.namaKelas=:namaKelas", Kelas.class)
                    .setParameter("namaKelas", namaKelas);
            return qry.getSingleResult();
        } catch (PersistenceException ex) {
            Logger.getLogger(NilaiManager.class).error(ex.getMessage());
            return null;
        }
    }

    public void persist(Kelas kelas) {
        try {
            em.persist(kelas);
        } catch (PersistenceException ex) {
            Logger.getLogger(NilaiManager.class).error(ex.getMessage());
        }
    }

    public void remove(Kelas kelas) {
        try {
            em.remove(em.merge(kelas));
        } catch (PersistenceException ex) {
            Logger.getLogger(NilaiManager.class).error(ex.getMessage());
        }
    }

    public void merge(Kelas kelas) {
        try {
            em.merge(kelas);
        } catch (PersistenceException ex) {
            Logger.getLogger(NilaiManager.class).error(ex.getMessage());
        }
    }
}
