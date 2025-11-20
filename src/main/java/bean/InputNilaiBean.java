package bean;

import repo.*;
import model.*;
import org.jboss.logging.Logger;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named
@ViewScoped
public class InputNilaiBean implements Serializable {
    List<MahasiswaKrsNilai> mhsKrsNilais;
    Long idMatkul;
    String kodeKelas;
    List<String> col;
    @EJB
    KrsMahasiswaManager krsMahasiswaManager;
    @EJB
    SkemaKrsNilaiManager skemaKrsNilaiManager;
    @EJB
    UserManager userManager;
    @Inject
    JenisNilaiBean jenisNilaiBean;
    @Inject
    MataKuliahBean mataKuliahBean;
    @Inject
    Nilai2Manager nilaiManager;

    @PostConstruct
    private void init() {
        mhsKrsNilais = new ArrayList<>();
    }

    public void reset2() {
        mhsKrsNilais.forEach(mkn->{
            if(mkn.getKrsNilai()!=null){
                mkn.getKrsNilai().getNilais().clear();
                List<Nilai2> nilais = mkn.getKrsNilai().getKrsMahasiswa().getNilai();
                nilais.forEach(n->{
                    for(String c:col){
                        if(n.getJenisNilai().getJenis().equals(c))
                            mkn.getKrsNilai().getNilais().put(c,n.getNilai());
                    }
                });
            }
        });
    }

    public void reset() {
        idMatkul = null;
        kodeKelas = null;
        col = new ArrayList<>();
        jenisNilaiBean.jenisNilais = null;
        mataKuliahBean.mataKuliahs = new ArrayList<>();
        mhsKrsNilais = new ArrayList<>();

    }

    public void simpan() {
        List<KrsMahasiswa> krs = new ArrayList<>();
        mhsKrsNilais.forEach(mkn -> {
            if (mkn.getKrsNilai() != null)
                krs.add(mkn.getKrsNilai().getKrsMahasiswa());
        });
        if (!krs.isEmpty() && !col.isEmpty()) {
            List<Nilai2> nilaiPersist = new ArrayList<>();
            List<Nilai2> nilaiMerge = new ArrayList<>();
            List<Nilai2> nilaiRemove = new ArrayList<>();

            for (MahasiswaKrsNilai mkn : mhsKrsNilais) {
                if (mkn.getKrsNilai() != null) {
                    KrsMahasiswa krsMahasiswa = mkn.getKrsNilai().getKrsMahasiswa();

                    col.forEach(c -> {
                        //mendapatkan nilai dari map
                        Double n = (Double) mkn.getKrsNilai().getNilais().get(c);
                        Nilai2 nkrs = null;
                        //System.out.println(krsMahasiswa.getNilai().size());
                        for (Nilai2 ni : krsMahasiswa.getNilai()) {
                            if (ni.getJenisNilai().getJenis().equals(c)) {
                                nkrs = ni;
                                //System.out.println("n1:"+n+" n2:"+ni.getNilai());
                            }
                        }

                        if (n != null && nkrs != null) {
                            if (!n.equals(nkrs.getNilai())) {
                                //System.out.println("merge " + c + ":" + n + "->" + nkrs.getNilai());
                                nkrs.setNilai(n);
                                nilaiMerge.add(nkrs);
                            }
                        } else {
                            if (n != null) {
                                //System.out.println("persist " + c + ":" + n);
                                Nilai2 newNilai = new Nilai2();
                                newNilai.setKrsMahasiswa(krsMahasiswa);
                                newNilai.setNilai(n);
                                newNilai.setJenisNilai(new JenisNilai());
                                newNilai.getJenisNilai().setJenis(c);
                                krsMahasiswa.getNilai().add(newNilai);
                                nilaiPersist.add(newNilai);
                            } else if (nkrs != null) {
                                //System.out.println("remove " + c + ":" + nkrs.getNilai());
                                nilaiRemove.add(nkrs);
                                krsMahasiswa.getNilai().remove(nkrs);
                            }
                        }
                    });
                }
            }
            try {
                nilaiManager.persist(nilaiPersist);
                nilaiManager.merge(nilaiMerge);
                nilaiManager.remove(nilaiRemove);

                FacesContext.getCurrentInstance().addMessage("", new FacesMessage("data berhasil disimpan"));
            }
            catch(Exception ex){
                FacesContext.getCurrentInstance().addMessage("", new FacesMessage("error",ex.getMessage()));
            }
        }
    }

    public void init2() {
        try {
            //mendapatkan list mahasiswa yang memiliki kode kelas tertentu
            //mahasiswas = mahasiswa2Bean.findMahasiswasByKodeKelas(kodeKelas);
            List<User>users = userManager.findUsersByKodeKelas(kodeKelas);
            if (!users.isEmpty()) {

                //mendapatkan krsmahasiswa
                List<KrsMahasiswa> krsMahasiswas = krsMahasiswaManager.findAllKrsMahasiswaAndGenerateNilaiByUsersAndIdMatakuliah(users, idMatkul);

                //mendapatkan idSkemakrs
                Long idSkemaKrs = krsMahasiswas.get(0).getSkedul().getIdSkemakrs().getId();

                //mendapatkan skemakrsnilai berdasarkan idskemakrs
                SkemaKrsNilai skemaKrsNilai = skemaKrsNilaiManager.findSkemaKrsNilaiByIdSkemaKrs(idSkemaKrs);
                mhsKrsNilais = new ArrayList<>();
                for (User u : users) {
                    MahasiswaKrsNilai mhsKrsNilai = new MahasiswaKrsNilai();
                    mhsKrsNilai.setMahasiswa(u.getMahasiswa());

                    for (KrsMahasiswa krs : krsMahasiswas) {
                        if (krs.getUser().getId().equals(u.getId())) {
                            KrsNilai krsNilai = new KrsNilai();
                            krsNilai.setKrsMahasiswa(krs);

                            skemaKrsNilai.getSkemaNilai().getSkemaNilaiDetail().forEach(snd -> {
                                String jn = snd.getJenisNilai().getJenis();
                                Double nilai = null;
                                for (Nilai2 n : krs.getNilai()) {
                                    if (n.getJenisNilai().getJenis().equals(jn)) {
                                        nilai = n.getNilai();
                                        break;
                                    }
                                }
                                krsNilai.getNilais().put(jn, nilai);
                            });

                            mhsKrsNilai.setKrsNilai(krsNilai);
                            break;
                        }
                    }
                    mhsKrsNilais.add(mhsKrsNilai);
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(InputNilaiBean.class).error("error:" + ex.getMessage());
            //ex.printStackTrace();
            FacesContext.getCurrentInstance().addMessage("", new FacesMessage("Error", ex.getMessage()));
        }
    }

    public Long getIdMatkul() {
        return idMatkul;
    }

    public void setIdMatkul(Long idMatkul) {
        this.idMatkul = idMatkul;
    }

    public String getKodeKelas() {
        return kodeKelas;
    }

    public void setKodeKelas(String kodeKelas) {
        this.kodeKelas = kodeKelas;
    }

    public List<MahasiswaKrsNilai> getMhsKrsNilais() {
        return mhsKrsNilais;
    }

    public void setMhsKrsNilais(List<MahasiswaKrsNilai> mhsKrsNilais) {
        this.mhsKrsNilais = mhsKrsNilais;
    }

    public List<String> getCol() {
        return col;
    }

    public void setCol(List<String> col) {
        this.col = col;
    }
}