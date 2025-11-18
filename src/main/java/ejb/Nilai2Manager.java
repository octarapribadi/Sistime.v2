package ejb;

import model.JenisNilai;
import model.KrsMahasiswa;
import model.Nilai;
import model.Nilai2;
import org.jboss.logging.Logger;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

@LocalBean
@Stateless
public class Nilai2Manager {
    @PersistenceContext
    EntityManager em;

    @EJB
    JenisNilaiManager jnManager;

    public List<Nilai2> findAllNilaiByKrsMahasiswas(List<KrsMahasiswa> krs) {
        try {
            TypedQuery<Nilai2> query = em.createQuery("select n from Nilai2 n " +
                            "where n.krsMahasiswa in :krs", Nilai2.class)
                    .setParameter("krs", krs);
            return query.getResultList();
        } catch (PersistenceException ex) {
            Logger.getLogger(Nilai2Manager.class).error(ex.getMessage());
            return null;
        }
    }

    public List<Nilai2> findAllNilaiByKrsMahasiswasAndJenisNilais(List<KrsMahasiswa> krs, List<String>jn) {
        try {
            TypedQuery<Nilai2> query = em.createQuery("select n from Nilai2 n " +
                            "where n.krsMahasiswa in :krs and n.jenisNilai.jenis in :jn", Nilai2.class)
                    .setParameter("krs", krs)
                    .setParameter("jn",jn);
            return query.getResultList();
        } catch (PersistenceException ex) {
            Logger.getLogger(Nilai2Manager.class).error(ex.getMessage());
            return null;
        }
    }

    public Boolean persist(Object n) {
        try {
            List<JenisNilai> jns = jnManager.findAllJenisNilai();
            if (n instanceof List<?>) {
                for (Object obj : (List<Nilai2>) n) {
                    if (obj instanceof Nilai2)
                        em.persist(obj);
                }
            } else if (n instanceof Nilai2) {
                em.persist(n);
            }
            return true;
        } catch (PersistenceException ex) {
            Logger.getLogger(Nilai2Manager.class).error(ex.getMessage());
            return false;
        }
    }

    public Boolean merge(Object n) {
        try {
            if (n instanceof List<?>) {
                for (Object obj : (List<Nilai2>) n) {
                    if (obj instanceof Nilai2)
                        em.merge(obj);
                }
            } else if (n instanceof Nilai2) {
                em.merge(n);
            }
            return true;
        } catch (PersistenceException ex) {
            Logger.getLogger(Nilai2Manager.class).error(ex.getMessage());
            return false;
        }
    }

    public Boolean remove(Object n) {
        try {
            if (n instanceof List<?>) {
                for (Object obj : (List<Nilai2>) n) {
                    if (obj instanceof Nilai2)
                        em.remove(em.merge(obj));
                }
            } else if (n instanceof Nilai2) {
                em.remove(em.merge(n));
            }
            return true;
        } catch (PersistenceException ex) {
            Logger.getLogger(Nilai2Manager.class).error(ex.getMessage());
            return false;
        }
    }


}
