package repo;

import entity.KrsMahasiswa;
import entity.Nilai;
import entity.User;
import org.jboss.logging.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class NilaiManager {

    @PersistenceContext
    EntityManager em;

    public List<Nilai> findNilaiByIdKrsMahasiswa(List<KrsMahasiswa> krsMahasiswas) {
        try {
            if (!krsMahasiswas.isEmpty()) {
                TypedQuery<Nilai> qry = em.createQuery("select n from Nilai n " +
                                "where n.krsMahasiswa in ?1 ",Nilai.class)
                        .setParameter(1, krsMahasiswas);
                List<Nilai>result = qry.getResultList();
                return result;
            }
            else
                return new ArrayList<Nilai>();
        } catch (PersistenceException ex)
        {
            Logger.getLogger(NilaiManager.class).error(ex.getMessage());
            return null;
        }
    }

    public List<Nilai> findNilaiByUsersAndidMatakuliah(List<User> users, Long idMatakuliah) {
        try {
            Query qry = em.createQuery("select n from Nilai n " +
                            "join fetch n.krsMahasiswa krs " +
                            "join krs.skedul s " +
                            "join s.idMatakuliah m " +
                            "where krs.user.id in ?1 and m.id=?2")
                    .setParameter(1, users)
                    .setParameter(2, idMatakuliah);
            return qry.getResultList();
        } catch (PersistenceException ex) {
            Logger.getLogger(NilaiManager.class).error(ex.getMessage());
            return null;
        }
    }

    public List<Nilai> findNilaiByIdUser(Long idUser) {
        try {
            Query qry = em.createQuery("select n from Nilai n " +
                            "join fetch n.krsMahasiswa k " +
                            "join fetch k.skedul s " +
                            "join fetch s.idSkemakrs skema " +
                            "join fetch skema.tahunAjaran " +
                            "join fetch s.idMatakuliah m " +
                            "where k.user.id=?1")
                    .setParameter(1, idUser);
            List<Nilai> result =  qry.getResultList();
            return result;
        } catch (PersistenceException ex) {
            Logger.getLogger(NilaiManager.class).error(ex.getMessage());
            return null;
        }
    }

    public void merge(Nilai nilai) {
        try {
            em.merge(nilai);
        } catch (PersistenceException ex) {
            Logger.getLogger(NilaiManager.class).error(ex.getMessage());
        }
    }

    public void persist(Nilai nilai) {
        try {
            em.persist(nilai);
        } catch (PersistenceException ex) {
            Logger.getLogger(NilaiManager.class).error(ex.getMessage());
        }
    }
}
