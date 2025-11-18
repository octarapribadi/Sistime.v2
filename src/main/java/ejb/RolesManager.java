package ejb;

import model.Roles;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.Set;

@Stateless
@LocalBean
public class RolesManager {
    @PersistenceContext
    EntityManager em;

    public Roles findRolesIdByRole(String role){
        try {
            Query qry = em.createQuery("select r from Roles r where r.role=?1").setParameter(1, role);
            return (Roles)qry.getSingleResult();
        }
        catch(NoResultException ex){
            return null;
        }
    }
}
