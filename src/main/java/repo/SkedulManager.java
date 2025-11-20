package repo;

import entity.Kelas;
import entity.Skedul;
import org.jboss.logging.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.*;
import java.util.List;

@ApplicationScoped
public class SkedulManager {
    @PersistenceContext
    EntityManager em;

    public void persist(Skedul skedul) {
        em.persist(skedul);
    }

    public void merge(Skedul skedul) {
        em.merge(skedul);
    }

    public void update(List<Skedul> skeduls, List<String> rowStatus) {
        for (int i = 0; i < skeduls.size(); i++) {
            if (rowStatus.get(i).equals("new")) {
                persist(skeduls.get(i));
            } else if (rowStatus.get(i).equals("edit")) {
                merge(skeduls.get(i));
            }
        }
    }

    public void remove(Skedul skedul) {
        em.remove(em.merge(skedul));
    }

    public List<Skedul> findAllSkedul(Long krsId) {
        Query qry = em.createQuery("select s from Skedul s join fetch s.idSkemakrs sk join fetch s.kelas k join fetch s.idDosen d join fetch s.idMatakuliah m " +
                        "where sk.id=?1")
                .setParameter(1, krsId);
        return qry.getResultList();
    }

    public List<Skedul> findAllSkedulAktifByKodeKelas(String kodeKelas) {
        TypedQuery<Skedul> query = em.createQuery("select s from Skedul s " +
                "join fetch s.idSkemakrs sk " +
                "join fetch s.idMatakuliah m " +
                "join fetch s.idDosen d " +
                "join fetch s.kelas k " +
                "where sk.aktif=true and s.kelas.kodeKelas=?1", Skedul.class
        ).setParameter(1, kodeKelas);
        try {
            return query.getResultList();
        } catch (PersistenceException ex) {
            return null;
        }
    }

    public List<Skedul> findAllSkedulAktifByKodeKelasAndKrsId(String kodeKelas, Long krsId) {
        TypedQuery<Skedul> query = em.createQuery("select s from Skedul s " +
                        "join s.idSkemakrs sk " +
                        "join fetch s.idMatakuliah m " +
                        "join fetch s.idDosen d " +
                        "join fetch s.kelas k " +
                        "where k.kodeKelas=?1 " +
                        "and sk.id=?2", Skedul.class)
                .setParameter(1, kodeKelas)
                .setParameter(2, krsId);
        try {
            return query.getResultList();
        } catch (PersistenceException ex) {
            return null;
        }
    }

    public List<Skedul> findAllSkedulAktifByProdiAndBelowAngkatan(Kelas kelas) {
        try {
            TypedQuery<Skedul> query = em.createQuery("select s from Skedul s " +
                            "join fetch s.idSkemakrs sk " +
                            "join fetch s.idMatakuliah m " +
                            "join fetch s.idDosen d " +
                            "join fetch s.kelas k " +
                            "where sk.aktif=true " +
                            "and k.angkatan>?1 " +
                            "and k.programStudi.kodeProgramstudi=?2 " +
                            "order by s.idMatakuliah.namaMatakuliah", Skedul.class)
                    .setParameter(1, kelas.getAngkatan())
                    .setParameter(2, kelas.getProgramStudi().getKodeProgramstudi());
            //.setParameter(2,kelas.getProgramStudi());
            return query.getResultList();
        } catch (PersistenceException ex) {
            Logger.getLogger(SkedulManager.class).error(ex.getMessage());
            return null;
        }
    }

    public Skedul findSkedulByKodeKelasAndIdMataKuliah(String kodeKelas, Long idMataKuliah) {
        try {
            Query query = em.createQuery("select s from Skedul s " +
                            "where s.kelas.kodeKelas=?1 and s.idMatakuliah.id=?2")
                    .setParameter(1, kodeKelas)
                    .setParameter(2, idMataKuliah);
            return (Skedul) query.getSingleResult();
        } catch (PersistenceException ex) {
            Logger.getLogger(SkedulManager.class).error(ex.getMessage());
            return null;
        }
    }

    public List<Skedul> findAllSkedulByKodeKelas(String kodeKelas) {
        try {
            TypedQuery<Skedul> query = em.createQuery("select s from Skedul s " +
                                    "join fetch s.idMatakuliah m " +
                                    "where s.kelas.kodeKelas=:kodekelas"
                    , Skedul.class)
                    .setParameter("kodekelas",kodeKelas);
            return query.getResultList();
        } catch (PersistenceException ex) {
            Logger.getLogger(SkedulManager.class).error("error:" + ex.getMessage());
            return null;
        }
    }

}
