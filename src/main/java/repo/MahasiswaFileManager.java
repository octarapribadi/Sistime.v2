package repo;

import model.MahasiswaFile;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;

@ApplicationScoped
public class MahasiswaFileManager {
    @PersistenceContext
    EntityManager em;

    public Boolean simpan(MahasiswaFile mahasiswaFile){
        try{
            em.persist(mahasiswaFile);
            return true;
        }
        catch(PersistenceException ex){
            System.err.println(ex.getMessage());
            return false;
        }
    }
}
