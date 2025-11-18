package bean;

import ejb.*;
import model.*;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named
@ViewScoped
public class KrsMahasiswaBean implements Serializable {
    private Integer maksimalSks = 0;
    Integer sksAtas = 0;
    Integer jumlahSks = 0;
    List<Skedul> skedulBawahList;
    List<Skedul> skedulList;
    List<KrsMahasiswa> krsMahasiswaList;
    Skedul selectedSkedul;
    @Inject
    SessionBean session;
    @EJB
    KrsMahasiswaManager krsMahasiswaManager;
    @EJB
    KelasManager kelasManager;
    @EJB
    StatusMahasiswaManager statusMahasiswaManager;
    @EJB
    SkedulManager skedulManager;
    @EJB
    MataKuliahManager mataKuliahManager;
    @EJB
    SettingsManager settingsManager;
    private StatusMahasiswa statusMahasiswa;

    public void lihatSkedul() {
        StatusMahasiswa sm = statusMahasiswaManager.findStatusMahasiswaByIdUser(session.getId());
        if (sm.getStatus().equals(StatusMahasiswa.AKTIF)) {
            skedulBawahList = new ArrayList<>();
            jumlahSks = sksAtas;
        }
    }

    private Skedul initSkedul() {
        Skedul skedul = new Skedul();
        skedul.setIdMatakuliah(new MataKuliah());
        skedul.setKodeKelas(new Kelas());
        skedul.setIdDosen(new Dosen());
        skedul.setIdSkemakrs(new SkemaKrs());
        return skedul;
    }

    public void init() {
        skedulBawahList = new ArrayList<>();
    }

    public void tambah() {
        skedulBawahList.add(initSkedul());
    }

    public void hapus(Skedul skedul) {
        skedulBawahList.remove(skedul);
        hitungSks();
    }

    public Integer findMaksimalSks() {
        maksimalSks = Integer.parseInt(settingsManager.findSetting("total_sks").getValue());
        return maksimalSks;
    }

    public void fillSkedulByKodeKelas() {
        statusMahasiswa = statusMahasiswaManager.findStatusMahasiswaByIdUser(session.getId());
        skedulList = skedulManager.findAllSkedulAktifByKodeKelas(statusMahasiswa.getKodeKelas().getKodeKelas());
        if (skedulList == null) {
            skedulList = new ArrayList<>();
            skedulList.add(initSkedul());
        }
        for (Skedul s : skedulList) {
            sksAtas += s.getIdMatakuliah().getSks();
        }
        jumlahSks = sksAtas;
    }

    @Transactional
    public void simpan() {
        if (jumlahSks > maksimalSks) {
            FacesContext.getCurrentInstance()
                    .addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Perhatian!", "Total sks melebihi " + maksimalSks));
        } else {
            try {
                krsMahasiswaManager.persist(session.getId(), skedulList, KrsMahasiswa.WAJIB);
                krsMahasiswaManager.persist(session.getId(), skedulBawahList, KrsMahasiswa.BAWAH);
                FacesContext.getCurrentInstance()
                        .addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Data berhasil disimpan", ""));
            } catch (Exception ex) {
                FacesContext.getCurrentInstance()
                        .addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", ex.getLocalizedMessage()));
            }
        }
    }

    public Boolean disable(Long idSkemaKrs) {
        return krsMahasiswaManager.isMahasiswaAlreadyFillSkedul(session.getId(), idSkemaKrs);
    }

    public void hitungSks() {
        //int sksBawah = mataKuliahManager.findSksByIdMataKuliah(idMatkul);
        int sksBawah = mataKuliahManager.findSksBySkedulList(skedulBawahList);
        jumlahSks = sksBawah + sksAtas;
    }

    public List<Skedul> findAllSkedulAktifByProdiAndBelowAngkatan() {
        Kelas kelas = kelasManager.findKelasByIdUser(session.getId());
        //System.out.println(kelas.getProgramStudi().getKodeProgramstudi());
        List<Skedul> listSkedul = skedulManager.findAllSkedulAktifByProdiAndBelowAngkatan(kelas);
        return listSkedul;
    }

    public void fillKrsMahasiswaList(Long idSkemaKrs) {
        krsMahasiswaList = krsMahasiswaManager.print(session.getId(), idSkemaKrs);
    }

    public List<Skedul> getSkedulBawahList() {
        return skedulBawahList;
    }

    public void setSkedulBawahList(List<Skedul> skedulBawahList) {
        this.skedulBawahList = skedulBawahList;
    }

    public Skedul getSelectedSkedul() {
        return selectedSkedul;
    }

    public void setSelectedSkedul(Skedul selectedSkedul) {
        this.selectedSkedul = selectedSkedul;
    }

    public Integer getSksAtas() {
        return sksAtas;
    }

    public void setSksAtas(Integer sksAtas) {
        this.sksAtas = sksAtas;
    }

    public List<Skedul> getSkedulList() {
        return skedulList;
    }

    public void setSkedulList(List<Skedul> skedulList) {
        this.skedulList = skedulList;
    }

    public Integer getJumlahSks() {
        return jumlahSks;
    }

    public void setJumlahSks(Integer jumlahSks) {
        this.jumlahSks = jumlahSks;
    }

    public List<KrsMahasiswa> getKrsMahasiswaList() {
        return krsMahasiswaList;
    }

    public void setKrsMahasiswaList(List<KrsMahasiswa> krsMahasiswaList) {
        this.krsMahasiswaList = krsMahasiswaList;
    }
}
