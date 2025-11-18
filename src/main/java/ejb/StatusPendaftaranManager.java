package ejb;

import model.StatusPendaftaran;
import org.jboss.logging.Logger;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import java.util.List;

@Stateless
@LocalBean
public class StatusPendaftaranManager {

    @PersistenceContext
    EntityManager em;

    public void persist(StatusPendaftaran statusPendaftaran){
        try {
            em.persist(statusPendaftaran);
        }
        catch(PersistenceException ex){
            Logger.getLogger(this.getClass()).log(Logger.Level.ERROR,ex);
        }
    }

    public void merge(StatusPendaftaran statusPendaftaran){
        try {
            em.merge(statusPendaftaran);
        }
        catch(PersistenceException ex){
            Logger.getLogger(this.getClass()).log(Logger.Level.ERROR,ex);
        }
    }

    public StatusPendaftaran findStatusByUserId(long userId){
        try {
            Query qry = em.createQuery("select s from StatusPendaftaran s where s.user.id=?1")
                    .setParameter(1,userId);
            List<StatusPendaftaran> statusPendaftaranList = qry.getResultList();
            if(!statusPendaftaranList.isEmpty())
                return statusPendaftaranList.get(0);
            else
                return null;
        }
        catch(PersistenceException ex){
            Logger.getLogger(this.getClass()).log(Logger.Level.ERROR,ex);
            return null;
        }
    }

    public List<StatusPendaftaran> findAllStatusPendaftaranByStatus(Byte status){
        try{
            Query qry = em.createQuery("select s from StatusPendaftaran s where s.status=?1")
                    .setParameter(1,status);
            List<StatusPendaftaran> statusPendaftaranList = qry.getResultList();
            if(!statusPendaftaranList.isEmpty()){
                return statusPendaftaranList;
            }
            return null;
        }
        catch(PersistenceException ex){
            Logger.getLogger(this.getClass()).log(Logger.Level.ERROR,ex);
            return null;
        }
    }

    public List<StatusPendaftaran> findAllStatusPendaftaran(){
        try{
            Query qry = em.createQuery("select s from StatusPendaftaran s");
            List<StatusPendaftaran> statusPendaftaranList = qry.getResultList();
            if(!statusPendaftaranList.isEmpty()){
                return statusPendaftaranList;
            }
            return null;
        }
        catch(PersistenceException ex){
            Logger.getLogger(this.getClass()).log(Logger.Level.ERROR,ex);
            return null;
        }
    }
}
