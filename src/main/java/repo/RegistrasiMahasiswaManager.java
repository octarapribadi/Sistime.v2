package repo;


import entity.RegistrasiMahasiswa;
import org.jboss.logging.Logger;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.PrePassivate;
import javax.enterprise.context.ApplicationScoped;
import javax.persistence.*;
import java.util.List;

@ApplicationScoped
public class RegistrasiMahasiswaManager {
    @PersistenceContext
    EntityManager em;

    @PostConstruct
    private void postConstruct(){
        System.out.println(this.getClass().toString()+" is constructed");
    }

    @PreDestroy
    private void preDestroy(){
        System.out.println(this.getClass().toString()+" is predestroy");
    }

    @PreRemove
    private void preRemove(){
        System.out.println(this.getClass().toString()+" is preRemove");
    }

    @PrePassivate
    private void prePassivate(){
        System.out.println(this.getClass().toString()+" is prePassivate");
    }

    @PostPersist
    private void postPersist(){
        System.out.println(this.getClass().toString()+" is postPersist");
    }

    RegistrasiMahasiswa registrasiMahasiswa;

    public RegistrasiMahasiswaManager(){}

    public void simpan(RegistrasiMahasiswa registrasiMahasiswa) {
        try {
            em.persist(registrasiMahasiswa);
            em.flush();
            System.out.println("data berhasil disimpan");

        }
        catch(PersistenceException ex){
            System.err.println(ex.getMessage());
        }
    }

    public Boolean findEmail(String email){
        Query qry = em.createQuery("select m.email from RegistrasiMahasiswa m where m.email=?1")
                .setParameter(1,email);

        if(qry.getResultList().size()>0)
            return true;
        else
            return false;
    }

    public Boolean konfirmasiKodeVerifikasi(String _kodeVerifikasi){

        Query qry = em.createQuery("select m.idPendaftaran from RegistrasiMahasiswa m where m.kodeVerifikasi=?1")
                .setParameter(1,_kodeVerifikasi);
        try{
            qry.getSingleResult();
            return true;
        }
        catch(NoResultException ex){
            return false;
        }
    }

    public List<RegistrasiMahasiswa> findAllRegistrasiMahasiswa(){
        try {
            Query qry = em.createQuery("select m from RegistrasiMahasiswa m");
            return qry.getResultList();
        }
        catch(PersistenceException ex){
            Logger.getLogger(RegistrasiMahasiswa.class).error("error:" + ex.getMessage());
        }
        return null;
    }

    public RegistrasiMahasiswa findRegistrasiMahasiswaByKodeVerifikasi(String _kodeVerifikasi){

        Query qry = em.createQuery("select m from RegistrasiMahasiswa m where m.kodeVerifikasi=?1")
                .setParameter(1,_kodeVerifikasi);
        try{
            return (RegistrasiMahasiswa)qry.getSingleResult();
        }
        catch(NoResultException ex){
            return null;
        }

        /*
        Query qry = em.createQuery("select m from RegistrasiMahasiswa m where m.kodeVerifikasi=?1")
                .setParameter(1,_kodeVerifikasi);
        RegistrasiMahasiswa x = (RegistrasiMahasiswa) qry.getSingleResult();
        x = em.find(RegistrasiMahasiswa.class,x.getIdPendaftaran());
        System.out.println("contains:"+em.contains(x));
        return x;

         */
    }

    public void isAttach(RegistrasiMahasiswa registrasiMahasiswa){
        System.out.println("isAttach?"+em.contains(em.merge(registrasiMahasiswa)));
    }

    public Boolean hapus(RegistrasiMahasiswa registrasiMahasiswa){
        try{
            em.remove(em.merge(registrasiMahasiswa));
            return true;
        }
        catch(PersistenceException ex){
            System.err.println(ex.getMessage());
            return false;
        }

    }

    public void merge(RegistrasiMahasiswa registrasiMahasiswa){
        em.merge(registrasiMahasiswa);
    }

}
