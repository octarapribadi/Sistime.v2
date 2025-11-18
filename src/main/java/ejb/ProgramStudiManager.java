package ejb;

import model.ProgramStudi;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;
import java.util.Map;

@Stateless
@LocalBean
public class ProgramStudiManager {

    ProgramStudi programStudi;

    @PersistenceContext
    EntityManager em;

    public List<ProgramStudi> findAllProgramStudi(){
        Query qry = em.createQuery("select x from ProgramStudi x");
        return qry.getResultList();
    }

    public ProgramStudi findProgramStudiById(String id){
        Query qry = em.createQuery("select p from ProgramStudi p where p.kodeProgramstudi=?1").setParameter(1,id);
        List<ProgramStudi> list = qry.getResultList();
        if(list.size()>0)
            return list.get(0);
        else
            return null;
    }

    public String findNamaProgramStudiById(String id){
        Query qry = em.createQuery("select p.namaProgramstudi from ProgramStudi p where p.kodeProgramstudi=?1").setParameter(1,id);
        List<ProgramStudi> list = qry.getResultList();
        if(list.size()>0)
            return list.get(0).getNamaProgramstudi();
        else
            return null;
    }

}
