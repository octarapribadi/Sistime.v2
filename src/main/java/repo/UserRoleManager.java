package repo;

import entity.UserRole;
import org.jboss.logging.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;
import java.util.List;

@ApplicationScoped
public class UserRoleManager {
    @PersistenceContext
    EntityManager em;

    public void persist(UserRole userRole) {
        em.persist(userRole);
    }

    public List<UserRole> findUserRoleByUserId(long userId) {
        return em.createQuery("select ur from UserRole ur " +
                        "join fetch ur.user u " +
                        "join fetch ur.role r " +
                        "where u.id = :userId", UserRole.class)
                .setParameter("userId", userId)
                .getResultList();
    }

    public List<UserRole> findUserRoleByUsername(String username) {
        return em.createQuery("select ur from UserRole ur " +
                        "join fetch ur.user u " +
                        "join fetch ur.role r " +
                        "where u.username = :username", UserRole.class)
                .setParameter("username", username)
                .getResultList();
    }

}
