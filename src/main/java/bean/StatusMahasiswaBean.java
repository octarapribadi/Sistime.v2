package bean;

import ejb.MahasiswaManager;
import ejb.StatusMahasiswaManager;
import model.Kelas;
import model.Mahasiswa;
import model.StatusMahasiswa;
import model.User;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named
@ViewScoped
public class StatusMahasiswaBean implements Serializable {
    private String status;
    List<Mahasiswa> mahasiswaList;
    Mahasiswa selectedMahasiswa;
    List<User> users;

    StatusMahasiswa statusMahasiswa;
    @EJB
    MahasiswaManager mahasiswaManager;

    @EJB
    StatusMahasiswaManager statusMahasiswaManager;

    @PostConstruct
    private void init(){
        users = new ArrayList<>();
        initStatusMahasiswa();
    }

    public void initStatusMahasiswas(){
        mahasiswaList = mahasiswaManager.findAllMahasiswaWithStatus();
    }

    public void initStatusMahasiswa(){
        statusMahasiswa = new StatusMahasiswa();
        statusMahasiswa.setUser(new User());
        statusMahasiswa.setKodeKelas(new Kelas());
    }
    public void lihatStatusMahasiswa(Mahasiswa mhs){
        selectedMahasiswa = mhs;
    }

    public void merge(){
        statusMahasiswaManager.merge(selectedMahasiswa.getUser().getStatusMahasiswa());
        FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_INFO,"Data berhasil dirubah",""));
    }

    public void persist(){
            statusMahasiswaManager.persist(statusMahasiswa);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Data berhasil ditambahkan",""));
    }

    public List<User> findAllMahasiswaTakBerstatus(){
        users = statusMahasiswaManager.findAllMahasiswaTakBerstatus();
        return users;
    }

    public void tambah(){
        initStatusMahasiswa();
        status="tambah";
    }

    public List<Mahasiswa> getMahasiswaList() {
        return mahasiswaList;
    }

    public void setMahasiswaList(List<Mahasiswa> mahasiswaList) {
        this.mahasiswaList = mahasiswaList;
    }

    public Mahasiswa getSelectedMahasiswa() {
        return selectedMahasiswa;
    }

    public void setSelectedMahasiswa(Mahasiswa selectedMahasiswa) {
        this.selectedMahasiswa = selectedMahasiswa;
    }

    public StatusMahasiswa getStatusMahasiswa() {
        return statusMahasiswa;
    }

    public void setStatusMahasiswa(StatusMahasiswa statusMahasiswa) {
        this.statusMahasiswa = statusMahasiswa;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
