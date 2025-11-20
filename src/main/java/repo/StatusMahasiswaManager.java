package repo;

import entity.StatusMahasiswa;
import entity.User;
import org.jboss.logging.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;
import java.util.List;

@ApplicationScoped
public class StatusMahasiswaManager {

    @PersistenceContext
    EntityManager em;

    public List<StatusMahasiswa> findAllStatusMahasiswa() {
        TypedQuery<StatusMahasiswa> query = em.createQuery("select s from StatusMahasiswa s", StatusMahasiswa.class);
        try {
            return query.getResultList();
        } catch (PersistenceException ex) {
            return null;
        }
    }

    public StatusMahasiswa findStatusMahasiswaByIdUser(long idUser) {
        try {
            TypedQuery<StatusMahasiswa> query = em.createQuery("select s from StatusMahasiswa s where s.user.id=?1", StatusMahasiswa.class)
                    .setParameter(1, idUser);

            return query.getSingleResult();
        } catch (PersistenceException ex) {
            return null;
        }
    }

    public StatusMahasiswa findStatusMahasiswaByNim(String nim){
        try {
            TypedQuery<StatusMahasiswa> query = em.createQuery("select s from StatusMahasiswa s " +
                            "join s.user u " +
                            "where s.nim=:nim", StatusMahasiswa.class)
                    .setParameter("nim", nim);
            return query.getSingleResult();
        } catch (PersistenceException ex) {
            Logger.getLogger(StatusMahasiswaManager.class).error(ex.getMessage());
            return null;
        }
    }


    public void merge(StatusMahasiswa statusMahasiswa) {
        try {
            //System.out.println(statusMahasiswa.getId());
            //System.out.println(statusMahasiswa.getKodeKelas().getKodeKelas());
            em.merge(statusMahasiswa);
        } catch (PersistenceException ex) {
            Logger.getLogger(StatusMahasiswaManager.class).log(Logger.Level.ERROR, ex);
        }
    }



    public List<User> findAllMahasiswaTakBerstatus() {
        try {
            TypedQuery<User> query = em.createQuery("select u from User u " +
                    "left join u.statusMahasiswa s " +
                    "join fetch u.mahasiswa m " +
                    "where s is null", User.class);
            List<User> users = query.getResultList();
            return users;
        } catch (PersistenceException ex) {
            return null;
        }
    }

    public void persist(StatusMahasiswa statusMahasiswa) {
        try {
            em.persist(statusMahasiswa);
        } catch (PersistenceException ex) {
            Logger.getLogger(StatusMahasiswaManager.class).log(Logger.Level.ERROR, ex);
        }
    }

    public Boolean checkNimExist(String nim) {
        try {
            TypedQuery<StatusMahasiswa> query = em.createQuery(
                            "select s from StatusMahasiswa s " +
                                    "where s.nim=?1", StatusMahasiswa.class)
                    .setParameter(1, nim);
            return query.getSingleResult() != null;
        } catch (PersistenceException ex) {
            return false;
        }
    }

}
