package repo;

import entity.SkemaKrsNilai;
import org.jboss.logging.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;
import java.util.List;

@ApplicationScoped
public class SkemaKrsNilaiManager {
    @PersistenceContext
    EntityManager em;

    public List<SkemaKrsNilai> findAllSkemaKrsNilai() {
        try {
            TypedQuery<SkemaKrsNilai> query = em.createQuery("select s from SkemaKrsNilai s " +
                            "join fetch s.skemaKrs sk " +
                            "join fetch s.skemaNilai sn " +
                            "join fetch sn.skemaNilaiDetail sd " +
                            "join fetch sd.jenisNilai jn "
                    , SkemaKrsNilai.class);
            return query.getResultList();
        } catch (PersistenceException ex) {
            Logger.getLogger(SkemaKrsNilaiManager.class).error(ex.getMessage());
            return null;
        }
    }

    public SkemaKrsNilai findSkemaKrsNilaiByIdSkemaKrs(Long idSkemaKrs) {
        try {
            TypedQuery<SkemaKrsNilai> query = em.createQuery("select s from SkemaKrsNilai s " +
                                    "join fetch s.skemaKrs sk " +
                                    "join fetch s.skemaNilai sn " +
                                    "join fetch sn.skemaNilaiDetail snd " +
                                    "where sk.id=:idSkemaKrs"
                            , SkemaKrsNilai.class)
                    .setParameter("idSkemaKrs", idSkemaKrs);
            return query.getSingleResult();
        } catch (PersistenceException ex) {
            Logger.getLogger(SkemaKrsNilaiManager.class).error(ex.getMessage());
            return null;
        }
    }
}
