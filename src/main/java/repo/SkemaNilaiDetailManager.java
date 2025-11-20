package repo;

import entity.SkemaNilaiDetail;
import org.jboss.logging.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;
import java.util.List;

@ApplicationScoped
public class SkemaNilaiDetailManager {
    @PersistenceContext
    EntityManager em;

    public List<SkemaNilaiDetail> findAllSkemaNilaiDetailByIdSkemaNilai(Long idSkemaNilai) {
        try {
            TypedQuery<SkemaNilaiDetail> query = em.createQuery("select snd from SkemaNilaiDetail snd " +
                                    "join fetch snd.jenisNilai jn " +
                                    "join snd.skemaNilai sn " +
                                    "where sn.id=:idSkemaNilai"
                            , SkemaNilaiDetail.class)
                    .setParameter("idSkemaNilai", idSkemaNilai);
            return query.getResultList();
        } catch (PersistenceException ex) {
            Logger.getLogger(SkemaNilaiDetailManager.class).error("error:" + ex.getMessage());
            return null;
        }
    }
}
