package repo;

import model.Mahasiswa;
import org.jboss.logging.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ApplicationScoped
public class MahasiswaManager {

    @PersistenceContext
    EntityManager em;

    public Long findJumlahMahasiswaByKodeProdi(String prodi) {
        try {
            Query query = em.createQuery("select count(m) from Mahasiswa m " +
                    "where m.programStudi.kodeProgramstudi=?1").setParameter(1, prodi);
            return (Long) query.getSingleResult();
        } catch (PersistenceException ex) {
            Logger.getLogger(MahasiswaManager.class).error(ex.getMessage());
            return null;
        }
    }

    public Mahasiswa findMahasiswaByNim(String nim){
        try {
            TypedQuery<Mahasiswa> query = em.createQuery("select m from Mahasiswa m " +
                            "join fetch m.user u " +
                            "join fetch u.statusMahasiswa sm " +
                            "join fetch sm.kelas k " +
                            "join fetch m.programStudi p " +
                            "where sm.nim=:nim", Mahasiswa.class)
                    .setParameter("nim", nim);
            return query.getSingleResult();
        } catch (PersistenceException ex) {
            Logger.getLogger(MahasiswaManager.class).error(ex.getMessage());
            return null;
        }
    }

    public Long findUserIdByEmail(String email) {
        try {
            Query query = em.createQuery("select m.user.id from Mahasiswa m where m.email=?1")
                    .setParameter(1, email);
            return (Long) query.getSingleResult();
        } catch (PersistenceException ex) {
            Logger.getLogger(MahasiswaManager.class).error(ex.getMessage());
            return null;
        }
    }

    public Map<Integer, Long> findJumlahAngkatan() {
        try {
            Map<Integer, Long> maps = new HashMap<Integer, Long>();
            Query query;
            query = em.createQuery("select distinct(m.tahunAngkatan) " +
                    "from Mahasiswa m " +
                    "group by m.tahunAngkatan");
            List<Integer> tahuns = query.getResultList();

            query = em.createQuery("select count(m.tahunAngkatan) " +
                    "from Mahasiswa m " +
                    "group by m.tahunAngkatan");
            List<Long> jumlah = query.getResultList();


            for (int i = 0; i < tahuns.size(); i++) {
                maps.put(tahuns.get(i), jumlah.get(i));
            }


            return maps;
        } catch (PersistenceException ex) {
            Logger.getLogger(MahasiswaManager.class).error(ex.getMessage());
            return null;
        }
    }

    public Mahasiswa findMahasiswaWithStatusMahasiswaByUserId(Long userId){
        try {
            TypedQuery<Mahasiswa> qry = em.createQuery("select m from Mahasiswa m " +
                            "join fetch m.user u " +
                            "join fetch u.statusMahasiswa s " +
                            "join fetch s.kelas k " +
                            "where m.user.id=?1", Mahasiswa.class)
                    .setParameter(1, userId);
            return qry.getSingleResult();
        } catch (NoResultException ex) {
            Logger.getLogger(MahasiswaManager.class).error(ex.getMessage());
            return null;
        }
    }

    public Mahasiswa findMahasiswaByIdUser(Long idUser) {
        try {
            TypedQuery<Mahasiswa> qry = em.createQuery("select m from Mahasiswa m " +
                            "join fetch m.user u " +
                            "left join fetch u.statusMahasiswa sm " +
                            "left join fetch sm.kelas k " +
                            "left join fetch m.sekolah " +
                            "left join fetch m.agama " +
                            "left join fetch m.kampus " +
                            "left join fetch m.programStudi " +
                            "left join fetch m.waktuKuliah " +
                            "left join fetch m.status " +
                            "where m.user.id=?1", Mahasiswa.class)
                    .setParameter(1, idUser);
            return qry.getSingleResult();
        } catch (NoResultException ex) {
            Logger.getLogger(MahasiswaManager.class).error(ex.getMessage());
            return null;
        }
    }

    public List<Mahasiswa> findAllMahasiswa() {
        Query qry = em.createQuery("select m from Mahasiswa m " +
                "LEFT JOIN FETCH m.programStudi " +
                "LEFT JOIN FETCH m.agama " +
                "LEFT JOIN FETCH m.status " +
                "LEFT JOIN FETCH m.sekolah " +
                "LEFT JOIN FETCH m.kampus " +
                "LEFT JOIN FETCH m.waktuKuliah w ");
        return qry.getResultList();
    }

    public List<Mahasiswa> findAllMahasiswaWithStatus() {
        try {
            TypedQuery<Mahasiswa> query = em.createQuery(
                    "select m from Mahasiswa m " +
                            "join fetch m.user u " +
                            "join fetch u.statusMahasiswa s " +
                            "join fetch s.kelas", Mahasiswa.class);
            return query.getResultList();
        } catch (PersistenceException ex) {
            Logger.getLogger(MahasiswaManager.class).error(ex.getMessage());
            return null;
        }

    }

    public List<Integer> findAllDistinctAngkatan() {
        try {
            Query query = em.createQuery(
                    "select m.tahunAngkatan from Mahasiswa m group by m.tahunAngkatan order by m.tahunAngkatan");
            return query.getResultList();
        } catch (PersistenceException ex) {
            Logger.getLogger(MahasiswaManager.class).error(ex.getMessage());
            return null;
        }
    }

    public List<Object[]> findCountAllMahasiswaAktifByTahunAngkatan() {
        try {
            Query qry = em.createQuery("select m.tahunAngkatan,count(m) from Mahasiswa m " +
                    "join m.user u " +
                    "join u.statusMahasiswa sm " +
                    "where sm.status='AKTIF' " +
                    "group by m.tahunAngkatan " +
                    "order by m.tahunAngkatan");
            List<Object[]> objects = qry.getResultList();
            return objects;
        } catch (PersistenceException ex) {
            Logger.getLogger(MahasiswaManager.class).error(ex.getMessage());
            return null;
        }
    }

    public List<Object[]> findAllMahasiswaByStatusWithAngkatan(String status) {
        try {
            Query qry = em.createQuery("select m.tahunAngkatan, count(m)  from Mahasiswa m " +
                            "join m.user u " +
                            "join u.statusMahasiswa s " +
                            "where s.status=?1 " +
                            "group by m.tahunAngkatan " +
                            "order by m.tahunAngkatan")
                    .setParameter(1, status);
            List<Object[]> objects = qry.getResultList();
            return objects;
        } catch (PersistenceException ex) {
            Logger.getLogger(StatusMahasiswaManager.class).log(Logger.Level.ERROR, ex);
            return null;
        }
    }

    public void persist(Mahasiswa mahasiswa) {
        em.persist(mahasiswa);
    }

    public void remove(Mahasiswa mahasiswa) {
        em.remove(em.merge(mahasiswa));
    }

    public void merge(Mahasiswa mahasiswa) {
        em.merge(mahasiswa);
    }

    public Boolean findEmail(String email) {
        Query qry = em.createQuery("select m.email from Mahasiswa m where m.email=?1")
                .setParameter(1, email);

        return !qry.getResultList().isEmpty();
    }

    public List<Mahasiswa>findMahasiswasByKodeKelas(String kodeKelas){
        try{
            TypedQuery<Mahasiswa> query = em.createQuery("select m from Mahasiswa m " +
                    "join fetch m.user u " +
                    "join fetch u.statusMahasiswa sm " +
                    "where sm.kelas.kodeKelas=:kodekelas",Mahasiswa.class)
                    .setParameter("kodekelas",kodeKelas);
            return query.getResultList();
        }
        catch(PersistenceException ex){
            Logger.getLogger(MahasiswaManager.class).error("error:" + ex.getMessage());
            return null;
        }
    }
}
