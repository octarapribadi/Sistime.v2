package repo;

import model.KrsMahasiswa;
import model.Skedul;
import model.User;
import org.jboss.logging.Logger;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.enterprise.context.ApplicationScoped;
import javax.persistence.*;
import java.util.List;

@ApplicationScoped
public class KrsMahasiswaManager {
    @PersistenceContext
    EntityManager em;

    public List<KrsMahasiswa> findKrsMahasiswaByUserId(Long id) {
        TypedQuery<KrsMahasiswa> query = em.createQuery("select k from KrsMahasiswa k " +
                                "join fetch k.skedul s " +
                                "join fetch s.idMatakuliah m " +
                                "join fetch s.idDosen d " +
                                "where k.user.id=?1",
                        KrsMahasiswa.class)
                .setParameter(1, id);
        try {
            List<KrsMahasiswa> krsMahasiswas = query.getResultList();
            return krsMahasiswas;
        } catch (PersistenceException ex) {
            return null;
        }
    }

    public Boolean isMahasiswaAlreadyFillSkedul(Long iduser, Long idSkemaKrs) {
        try {
            TypedQuery<KrsMahasiswa> query = em.createQuery("select k from KrsMahasiswa k " +
                            "join fetch k.skedul s " +
                            "join fetch s.idSkemakrs skema " +
                            "join fetch k.user u " +
                            "where u.id=?1 and skema.id=?2", KrsMahasiswa.class)
                    .setParameter(1, iduser)
                    .setParameter(2, idSkemaKrs);
            return !query.getResultList().isEmpty();

        } catch (PersistenceException ex) {
            Logger.getLogger(KrsMahasiswaManager.class).error(ex.getMessage());
            return false;
        }
    }


    public List<KrsMahasiswa> findKrsMahasiswaByUsersAndIdMatakuliah(List<User> users, Long idMatakuliah) {
        try {
            TypedQuery<KrsMahasiswa> query = em.createQuery("select k from KrsMahasiswa k " +
                            "join fetch k.skedul s " +
                            "join fetch s.idMatakuliah m " +
                            "where k.user in :users and m.id=:idMatkul", KrsMahasiswa.class)
                    .setParameter("users", users)
                    .setParameter("idMatkul", idMatakuliah);
            return query.getResultList();
        } catch (PersistenceException ex) {
            Logger.getLogger(KrsMahasiswaManager.class).error("error:" + ex.getMessage());
            return null;
        }
    }

    public List<KrsMahasiswa> findAllKrsMahasiswaAndGenerateNilaiByUsersAndIdMatakuliah(List<User> users, Long idMatkul) {
        try {
            TypedQuery<KrsMahasiswa> query = em.createQuery("select k from KrsMahasiswa k " +
                            "join fetch k.skedul s " +
                            "join fetch s.idMatakuliah m " +
                            "left join fetch k.nilai n " +
                            "where k.user in :users and m.id=:idMatkul", KrsMahasiswa.class)
                    .setParameter("users", users)
                    .setParameter("idMatkul", idMatkul);
            return query.getResultList();
        } catch (PersistenceException ex) {
            Logger.getLogger(KrsMahasiswaManager.class).error("error:" + ex.getMessage());
            return null;
        }
    }

    public void persist(Long userId, List<Skedul> skeduls, Integer tipe_skedul) {
        try {
            for (Skedul s : skeduls) {
                //System.out.println(s.getId());
                KrsMahasiswa krsMahasiswa = new KrsMahasiswa();
                krsMahasiswa.setSkedul(s);
                User user = new User();
                user.setId(userId);
                krsMahasiswa.setUser(user);
                krsMahasiswa.setTipeSkedul(tipe_skedul);
                em.persist(krsMahasiswa);
            }
        } catch (PersistenceException ex) {
            Logger.getLogger(KrsMahasiswaManager.class).log(Logger.Level.ERROR, ex);
            throw new PersistenceException(ex);
        }
    }

    public List<User> findAllKrsBySkemaKrs(Long idSkemaKrs) {
        try {
            TypedQuery<User> query = em.createQuery("select distinct (u) from KrsMahasiswa k " +
                            "join k.user u " +
                            "join k.skedul s " +
                            "join s.idSkemakrs sk " +
                            "join fetch u.statusMahasiswa st " +
                            "join fetch u.mahasiswa m " +
                            "join fetch st.kelas kls " +
                            "where sk.id=?1 " +
                            "order by kls.kodeKelas", User.class)
                    .setParameter(1, idSkemaKrs);
            return query.getResultList();
        } catch (PersistenceException ex) {
            return null;
        }
    }

    public List<KrsMahasiswa> findAllKrsMahasiswaByUserAndKrsId(Long userId, Long krsId) {
        try {
            TypedQuery<KrsMahasiswa> query = em.createQuery(
                            "select k from KrsMahasiswa  k " +
                                    "join fetch k.user u " +
                                    "join fetch k.skedul s " +
                                    "join s.idSkemakrs skema " +
                                    "join fetch s.idMatakuliah m " +
                                    "join fetch s.kelas kelas " +
                                    "where u.id=?1 and skema.id=?2"
                            , KrsMahasiswa.class)
                    .setParameter(1, userId)
                    .setParameter(2, krsId);
            return query.getResultList();
        } catch (PersistenceException ex) {
            return null;
        }
    }

    public List<KrsMahasiswa> print(Long idUser, Long idSkemaKrs) {
        TypedQuery<KrsMahasiswa> query = em.createQuery("select k from KrsMahasiswa k " +
                                "join fetch k.skedul s " +
                                "join fetch s.idMatakuliah " +
                                "join fetch s.idDosen " +
                                "join fetch s.idSkemakrs skema " +
                                "join fetch k.user u " +
                                "where u.id=?1 and skema.id=?2"
                        , KrsMahasiswa.class)
                .setParameter(1, idUser)
                .setParameter(2, idSkemaKrs);

        try {
            return query.getResultList();
        } catch (PersistenceException ex) {
            return null;
        }
    }

    public void removeByList(List<KrsMahasiswa> krsMahasiswas) {
        try {
            Query query = em.createQuery("delete from KrsMahasiswa krs " +
                            "where krs in ?1")
                    .setParameter(1, krsMahasiswas);
            query.executeUpdate();
        } catch (PersistenceException ex) {
            Logger.getLogger(KrsMahasiswaManager.class).log(Logger.Level.ERROR, ex.getMessage());
        }
    }

    public List<KrsMahasiswa> findAllKrsMahasiswaByUserIdAndSkemaKrsId(Long userId, Long skemaKrsId) {
        try {
            TypedQuery<KrsMahasiswa> query = em.createQuery("select k from KrsMahasiswa k " +
                                    "join fetch k.user u " +
                                    "join fetch k.skedul s " +
                                    "join fetch s.idSkemakrs sk " +
                                    "join fetch s.idMatakuliah m " +
                                    "where sk.id=:skId and u.id=:uId"
                            , KrsMahasiswa.class)
                    .setParameter("skId", skemaKrsId)
                    .setParameter("uId", userId);
            return query.getResultList();
        } catch (PersistenceException ex) {
            Logger.getLogger(KrsMahasiswaManager.class).error("error:" + ex.getMessage());
            return null;
        }
    }
}


