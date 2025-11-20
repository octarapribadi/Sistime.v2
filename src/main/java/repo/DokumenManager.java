package repo;

import model.Dokumen;
import model.JenisDokumen;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

@ApplicationScoped
public class DokumenManager {

    @PersistenceContext
    EntityManager em;

    public List<Dokumen> findDokumenByUserId(long idUser) {
            TypedQuery<Dokumen> qry = em.createQuery("select d from Dokumen d where d.idUser=?1", Dokumen.class);
            qry.setParameter(1, idUser);
            return qry.getResultList();
    }

    public List<JenisDokumen> findAllJenisDokumen() {
            TypedQuery<JenisDokumen> qry = em.createQuery("select j from JenisDokumen j", JenisDokumen.class);
            return qry.getResultList();
    }

    public void persist(Dokumen dokumen) {
            em.persist(dokumen);
    }

    public void merge(Dokumen dokumen) {
            em.merge(dokumen);
    }

    public void delete(Dokumen dokumen) {
            em.remove(em.merge(dokumen));
    }
}
