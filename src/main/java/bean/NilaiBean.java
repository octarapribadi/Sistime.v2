package bean;

import repo.*;
import model.*;
import org.primefaces.event.CellEditEvent;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named
@ViewScoped
public class NilaiBean implements Serializable {
    @EJB
    NilaiManager nilaiManager;
    @EJB
    UserManager userManager;
    @EJB
    MataKuliahManager mataKuliahManager;
    @EJB
    KrsMahasiswaManager krsMahasiswaManager;
    List<MahasiswaNilai> mahasiswaNilais;
    List<Nilai> nilais;
    List<User> users;
    List<KrsMahasiswa> krsMahasiswas;
    MataKuliah selectedMataKuliah;
    Kelas selectedKelas;

    @PostConstruct
    public void init() {
        selectedMataKuliah = new MataKuliah();
        selectedKelas = new Kelas();
    }

    public List<MahasiswaNilai> generateMahasiswaNilai() {
        //dapatkan objek matakuliah
        selectedMataKuliah = mataKuliahManager.findMataKuliahById(selectedMataKuliah.getId());

        //dapat list user berdasarkan kelas
        users = userManager.findUsersByKodeKelas(selectedKelas.getKodeKelas());

        //dapatkan seluruh krsmahasiswa berdasarkan user dan idmatkuliah
        krsMahasiswas = krsMahasiswaManager
                .findKrsMahasiswaByUsersAndIdMatakuliah(users, selectedMataKuliah.getId());

        //dapatkan seluruh list nilai bedasarkan user dan krsmahasiswa
        nilais = nilaiManager.findNilaiByIdKrsMahasiswa(krsMahasiswas);

        mahasiswaNilais = new ArrayList<>();
        MahasiswaNilai mn;
        for (User u : users) {
            mn = new MahasiswaNilai();
            mn.setNim(u.getStatusMahasiswa().getNim());
            mn.setNamaMahasiswa(u.getMahasiswa().getNamaMahasiswa());

            for (KrsMahasiswa k : krsMahasiswas) {
                if (u.getId().equals(k.getUser().getId())) {
                    mn.setKrsMahasiswa(k);
                    break;
                }
            }
            boolean ketemu = false;
            for (Nilai n : nilais) {
                if (mn.getKrsMahasiswa()!=null && n.getKrsMahasiswa().getId().equals(mn.getKrsMahasiswa().getId())) {
                    mn.setAbsensi(n.getAbsensi());
                    mn.setTugas(n.getTugas());
                    mn.setUts(n.getUts());
                    mn.setUas(n.getUas());
                    mn.setKeterangan(n.getKeterangan());
                    ketemu = true;
                    break;
                }
            }
            if (!ketemu) {
                mn.setAbsensi(null);
                mn.setTugas(null);
                mn.setUts(null);
                mn.setUas(null);
                mn.setKeterangan(null);
            }
            mahasiswaNilais.add(mn);
        }
        return mahasiswaNilais;
    }

    public void toeng2(CellEditEvent<Double> event) {
        MahasiswaNilai mn2 = mahasiswaNilais.get(event.getRowIndex());
        boolean status = false;
        for (Nilai n : nilais) {
            if (n.getKrsMahasiswa().getId().equals(mn2.getKrsMahasiswa().getId())) {
                n.setAbsensi(mn2.getAbsensi());
                n.setTugas(mn2.getTugas());
                n.setUts(mn2.getUts());
                n.setUas(mn2.getUas());
                n.setKeterangan(mn2.getKeterangan());
                nilaiManager.merge(n);
                status = true;
                break;
            }
        }
        if (!status) {
            Nilai nilai = new Nilai();
            nilai.setKrsMahasiswa(mn2.getKrsMahasiswa());
            nilai.setAbsensi(mn2.getAbsensi());
            nilai.setTugas(mn2.getTugas());
            nilai.setUts(mn2.getUts());
            nilai.setUas(mn2.getUas());
            nilai.setKeterangan(mn2.getKeterangan());
            nilais.add(nilai);
            nilaiManager.persist(nilai);
        }
    }

    public List<MahasiswaNilai> getMahasiswaNilais() {
        return mahasiswaNilais;
    }

    public void setMahasiswaNilais(List<MahasiswaNilai> mahasiswaNilais) {
        this.mahasiswaNilais = mahasiswaNilais;
    }

    public MataKuliah getSelectedMataKuliah() {
        return selectedMataKuliah;
    }

    public void setSelectedMataKuliah(MataKuliah selectedMataKuliah) {
        this.selectedMataKuliah = selectedMataKuliah;
    }

    public Kelas getSelectedKelas() {
        return selectedKelas;
    }

    public void setSelectedKelas(Kelas selectedKelas) {
        this.selectedKelas = selectedKelas;
    }

    public List<Nilai> getNilais() {
        return nilais;
    }

    public void setNilais(List<Nilai> nilais) {
        this.nilais = nilais;
    }

}
