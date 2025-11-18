package ejb;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import model.JenisNilai;
import org.jboss.logging.Logger;

import java.util.List;

@LocalBean
@Stateless
public class JenisNilaiManager {
    @PersistenceContext
    EntityManager em;

    public List<JenisNilai> findAllJenisNilai() {
        try {
            TypedQuery<JenisNilai> query = em.createQuery("select jn from JenisNilai jn", JenisNilai.class);
            return query.getResultList();
        } catch (PersistenceException ex) {
            Logger.getLogger(JenisNilaiManager.class).error(ex.getMessage());
            return null;
        }
    }

    public JenisNilai findJenisNilaiByJenis(String jenis) {
        try {
            TypedQuery<JenisNilai> query = em.createQuery("select jn from JenisNilai jn " +
                            "where jn.jenis like :jenis", JenisNilai.class)
                    .setParameter("jenis", jenis);
            return query.getSingleResult();
        } catch (PersistenceException ex) {
            Logger.getLogger(JenisNilaiManager.class).error(ex.getMessage());
            return null;
        }
    }

}
