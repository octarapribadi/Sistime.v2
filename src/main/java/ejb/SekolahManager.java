package ejb;

import model.Sekolah;
import org.jboss.logging.Logger;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.*;
import java.util.List;

@Stateless
@LocalBean
public class SekolahManager {

    @PersistenceContext
    EntityManager em;

    Sekolah sekolah;

    public Sekolah getSekolah() {
        return sekolah;
    }

    public void setSekolah(Sekolah sekolah) {
        this.sekolah = sekolah;
    }

    public List<Sekolah> findAll(){
        Query qry = em.createQuery("select s from Sekolah s");
        return qry.getResultList();
    }

    public Sekolah findById(int idSekolah){
        TypedQuery<Sekolah> qry = em.createQuery("select s from Sekolah s where s.idSekolah=?1",Sekolah.class);
        qry.setParameter(1,idSekolah);
        return qry.getResultList().size()>0?(Sekolah)qry.getResultList().get(0):null;
    }

    public int findIdSekolahByNamaSekolah(String namaSekolah){
        TypedQuery<Sekolah>qry = em.createQuery("select s from Sekolah s where s.namaSekolah=?1",Sekolah.class)
                .setParameter(1,namaSekolah);
        List <Sekolah> list=qry.getResultList();
        if(list.size()>0){
            return list.get(0).getIdSekolah();
        }
        else{
            return -1;
        }
    }

    public Sekolah findSekolahByNama(String nama){
        try{
            TypedQuery<Sekolah>qry = em.createQuery("select s from Sekolah s where s.namaSekolah=?1",Sekolah.class)
                    .setParameter(1,nama);
            return qry.getSingleResult();
        }
        catch(PersistenceException ex){
            return null;
        }
    }

    public void persist(Sekolah sekolah){
        try{
            em.persist(sekolah);
        }
        catch(PersistenceException ex){
            Logger.getLogger(SekolahManager.class).error("error: "+ ex.getMessage());
        }
    }

    public void merge(Sekolah sekolah){
        try{
            em.merge(sekolah);
        }
        catch(PersistenceException ex){
            Logger.getLogger(SekolahManager.class).error("error: "+ ex.getMessage());
        }
    }

}
