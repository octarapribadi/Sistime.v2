package ejb;

import model.MahasiswaFile;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;

@LocalBean
@Stateless
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
