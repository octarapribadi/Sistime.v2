package bean;

import ejb.MahasiswaManager;
import model.*;

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
public class Mahasiswa2Bean implements Serializable {
    @Inject
    SessionBean session;

    @EJB
    MahasiswaManager mahasiswaManager;

    Mahasiswa selectedMahasiswa;
    Integer idSekolah;
    String status;

    List<Mahasiswa>mahasiswas;
    List<Mahasiswa>filteredMahasiswa;
    @PostConstruct
    public void init(){
        selectedMahasiswa = new Mahasiswa();
        mahasiswas = new ArrayList<>();
        filteredMahasiswa = new ArrayList<>();
    }


    public List<String> pengumuman(){
        List<String> pengumumans = new ArrayList<>();
        pengumumans.add(new String("<b>tes1</b>"));
        pengumumans.add(new String("<i>tes2</i>"));
        pengumumans.add(new String("tes3"));
        pengumumans.add(new String("tes4"));
        pengumumans.add(new String("tes5"));

        return pengumumans;
    }

    public void initMahasiswa() {
        selectedMahasiswa = mahasiswaManager.findMahasiswaByIdUser(session.getId());
        if(selectedMahasiswa.getProgramStudi()==null)selectedMahasiswa.setProgramStudi(new ProgramStudi());
        if(selectedMahasiswa.getKampus()==null) selectedMahasiswa.setKampus(new Kampus());
        if(selectedMahasiswa.getWaktuKuliah()==null) selectedMahasiswa.setWaktuKuliah(new WaktuKuliah());
        if(selectedMahasiswa.getSekolah()==null) selectedMahasiswa.setSekolah(new Sekolah());
        if(selectedMahasiswa.getAgama()==null) selectedMahasiswa.setAgama(new Agama());
        if(selectedMahasiswa.getStatus()==null) selectedMahasiswa.setStatus(new Status());
    }

    public List<Mahasiswa> findMahasiswasByKodeKelas(String kodeKelas){
        mahasiswas = mahasiswaManager.findMahasiswasByKodeKelas(kodeKelas);
        return mahasiswas;
    }

    public void merge() {
        if (selectedMahasiswa.getAgama().getIdAgama() == null) selectedMahasiswa.setAgama(null);
        //if(selectedMahasiswa.getSekolah().getIdSekolah()==null) selectedMahasiswa.setSekolah(null);
        if (selectedMahasiswa.getStatus().getIdStatus() == -1) selectedMahasiswa.setStatus(null);
        //System.out.println(selectedMahasiswa.getStatus() == null);
        mahasiswaManager.merge(selectedMahasiswa);
        FacesContext.getCurrentInstance().addMessage("", new FacesMessage("Perubahan profile berhasil!"));
    }

    public void findAllMahasiswa() {
        mahasiswas = mahasiswaManager.findAllMahasiswa();
    }

    public Mahasiswa getSelectedMahasiswa() {
        return selectedMahasiswa;
    }

    public void setSelectedMahasiswa(Mahasiswa selectedMahasiswa) {
        this.selectedMahasiswa = selectedMahasiswa;
    }

    public Integer getIdSekolah() {
        return idSekolah;
    }

    public void setIdSekolah(Integer idSekolah) {
        this.idSekolah = idSekolah;
    }

    public List<Mahasiswa> getMahasiswas() {
        return mahasiswas;
    }

    public void setMahasiswas(List<Mahasiswa> mahasiswas) {
        this.mahasiswas = mahasiswas;
    }

    public List<Mahasiswa> getFilteredMahasiswa() {
        return filteredMahasiswa;
    }

    public void setFilteredMahasiswa(List<Mahasiswa> filteredMahasiswa) {
        this.filteredMahasiswa = filteredMahasiswa;
    }
}
