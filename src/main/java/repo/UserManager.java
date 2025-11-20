package repo;

import model.User;
import org.jboss.logging.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@ApplicationScoped
public class UserManager {
    @PersistenceContext
    EntityManager em;

    User user;

    public List<User> findAll() {
        Query qry = em.createQuery("select u from User u");
        List resultList = qry.getResultList();
        return resultList;
    }

    public User findUser(String username) {
        try {
            Query qry = em.createQuery("select u from User u where u.username=?1").setParameter(1, username);
            user = (User) qry.getSingleResult();
            return user;
        } catch (NoResultException ex) {
            return null;
        }
    }

    public List<User> findUsersByKodeKelas(String kodeKelas) {
        try {
            Query qry = em.createQuery("select u from User u " +
                            "join fetch u.mahasiswa m " +
                            "join fetch u.statusMahasiswa s " +
                            "where s.kelas.kodeKelas=?1 " +
                            "order by s.nim")
                    .setParameter(1, kodeKelas);
            return qry.getResultList();
        } catch (Exception ex) {
            Logger.getLogger(UserManager.class).error(ex.getMessage());
            return null;
        }
    }

    public void persist(User user) {
        em.persist(user);
        em.flush();
    }

    public void merge(User user) {
        em.merge(user);
        em.flush();
    }

    public User findUserByUserId(long userId) {
        try {
            Query qry = em.createQuery("select u from User u where u.id=?1")
                    .setParameter(1, userId);
            return (User)qry.getSingleResult();
        } catch (Exception ex) {
            Logger.getLogger(UserManager.class).error(ex.getMessage());
            return null;
        }
    }
}
