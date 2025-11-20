package repo;

import model.Registrasi;
import org.jboss.logging.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import java.util.List;

@ApplicationScoped
public class RegistrasiManager {
    @PersistenceContext
    EntityManager em;

    public Boolean findEmail(String email) {
        Query qry = em.createQuery("select r.email from Registrasi r where r.email=?1")
                .setParameter(1, email);

        return !qry.getResultList().isEmpty();
    }

    public void simpan(Registrasi registrasi){
        try {
            em.persist(registrasi);
            //em.flush();
        }
        catch(PersistenceException ex){
            Logger.getLogger(RegistrasiManager.class).log(Logger.Level.ERROR,ex.getMessage());
        }
    }

    public void hapus(Registrasi registrasi){
        try{
            em.remove(em.merge(registrasi));
        }
        catch(PersistenceException ex){
            Logger.getLogger(RegistrasiManager.class).log(Logger.Level.ERROR,ex.getMessage());
        }

    }

    public Registrasi findRegistrasiByVerification(String verificationCode){
        try{
            Query qry = em.createQuery("select r from Registrasi r where verifikasi=?1").setParameter(1,verificationCode);
            return (Registrasi)qry.getSingleResult();
        }
        catch(PersistenceException ex){
            return null;
        }
    }

    public List<Registrasi> findAllRegistrasi(){
        try{
            Query qry = em.createQuery("select r from Registrasi r");
            return qry.getResultList();
        }
        catch(PersistenceException ex){
            Logger.getLogger(RegistrasiManager.class).log(Logger.Level.ERROR,ex.getMessage());
            return null;
        }
    }

}
