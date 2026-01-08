package repo;

import entity.User;
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

    public List<User> findAll() {
        Query qry = em.createQuery("select u from User u");
        List resultList = qry.getResultList();
        return resultList;
    }

    public User findUser(String username) {
        Query qry = em.createQuery("select u from User u where u.username=?1").setParameter(1, username);
        User user = (User) qry.getSingleResult();
        return user;
    }

    public List<User> findUsersByKodeKelas(String kodeKelas) {
        Query qry = em.createQuery("select u from User u " +
                        "join fetch u.mahasiswa m " +
                        "join fetch u.statusMahasiswa s " +
                        "where s.kelas.kodeKelas=?1 " +
                        "order by s.nim")
                .setParameter(1, kodeKelas);
        return qry.getResultList();
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
        Query qry = em.createQuery("select u from User u where u.id=?1")
                .setParameter(1, userId);
        return (User) qry.getSingleResult();
    }

    public User findUserByUsername(String username) {
        Query qry = em.createQuery("select u from User u where u.username=?1")
                .setParameter(1, username);
        return (User) qry.getSingleResult();
    }

}
