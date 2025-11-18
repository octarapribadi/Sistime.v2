package bean;

import ejb.MahasiswaManager;
import ejb.ProgramStudiManager;
import ejb.SekolahManager;
import model.*;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.file.UploadedFile;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.persistence.PersistenceException;
import java.io.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Named
@ViewScoped
public class MahasiswaBean implements Serializable {

    @EJB
    MahasiswaManager mahasiswaManager;

    @EJB
    ProgramStudiManager programStudiManager;

    @EJB
    SekolahManager sekolahManager;

    Mahasiswa selectedMahasiswa;
    List<Mahasiswa> mahasiswas;
    String _idSekolah;
    UploadedFile uploadedFile;
    private String status = ""; //new,edit

    List<Mahasiswa> filteredMahasiswa;

    @PostConstruct
    public void init() {
        mahasiswas = new ArrayList<>();
        selectedMahasiswa = new Mahasiswa();
        filteredMahasiswa = new ArrayList<>();
        //mahasiswas = mahasiswaManager.findAllMahasiswa();
        //System.out.println("MahasiswaBean constructed");
        //System.out.println("mahasiswas size:" + mahasiswas.size());
    }

    public Mahasiswa findMahasiswaByIdUser(long idUser){
        return mahasiswaManager.findMahasiswaByIdUser(idUser);
    }

    public void findAllMahasiswa() {
        mahasiswas = mahasiswaManager.findAllMahasiswa();
    }

    public List<ProgramStudi> findALlProgramstudi() {
        return programStudiManager.findAllProgramStudi();
    }

    public void openNew() {
        status = "new";

        selectedMahasiswa = new Mahasiswa();
        selectedMahasiswa.setKampus(new Kampus());
        selectedMahasiswa.setWaktuKuliah(new WaktuKuliah());
        selectedMahasiswa.setAgama(new Agama());
        selectedMahasiswa.setProgramStudi(new ProgramStudi());
        selectedMahasiswa.setStatus(new Status());
        selectedMahasiswa.setSekolah(new Sekolah());
        //System.out.println("openNew()");
        //System.out.println(selectedMahasiswa.getStatus()==null);
    }

    public void deleteSelected() {
        if (selectedMahasiswa != null) {
            mahasiswaManager.remove(selectedMahasiswa);
            mahasiswas.remove(selectedMahasiswa);
            //System.out.println("mahasiswas size after delete:" + mahasiswas.size());
            selectedMahasiswa = null;
            FacesContext.getCurrentInstance()
                    .addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO
                            , "Data mahasiswa berhasil dihapus!", ""));
        } else {
            FacesContext.getCurrentInstance()
                    .addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO
                            , "Kamu belum memilih data mahasiswa", ""));
        }

    }

    public void save2() {
        System.out.println(selectedMahasiswa.getProgramStudi() == null);
    }

    public void save() {
        FacesContext ctx = FacesContext.getCurrentInstance();
        try {
            if (selectedMahasiswa.getAgama().getIdAgama() == null) selectedMahasiswa.setAgama(null);
            if (selectedMahasiswa.getStatus().getIdStatus() == -1) selectedMahasiswa.setStatus(null);
            if (_idSekolah != null)
                selectedMahasiswa.getSekolah().setIdSekolah(sekolahManager.findIdSekolahByNamaSekolah(_idSekolah));
            if (uploadedFile != null) handleUploadedFile();

            if (status.equals("new")) {
                mahasiswaManager.persist(selectedMahasiswa);
                mahasiswas.add(selectedMahasiswa);
                ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Data berhasil ditambahkan", ""));
            } else if (status.equals("edit")) {
                mahasiswaManager.merge(selectedMahasiswa);
                ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Data berhasil diubah", ""));
            }
            status = null;
            selectedMahasiswa = null;
        } catch (PersistenceException ex) {
            System.err.println(ex.getMessage());
            ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Data gagal ditambahkan", ex.getMessage()));
        }
    }

    public void cancelNew() {
        //selectedMahasiswa = null;
        status = null;
        //System.out.println("cancelNew()");
    }

    public void print() {
        if (selectedMahasiswa != null)
            System.out.println(selectedMahasiswa.getProgramStudi().getKodeProgramstudi());
    }

    public void edit() {
        status = "edit";
        if (selectedMahasiswa.getKampus() == null) selectedMahasiswa.setKampus(new Kampus());
        if (selectedMahasiswa.getWaktuKuliah() == null) selectedMahasiswa.setWaktuKuliah(new WaktuKuliah());
        if (selectedMahasiswa.getAgama() == null) selectedMahasiswa.setAgama(new Agama());
        if (selectedMahasiswa.getStatus() == null) selectedMahasiswa.setStatus(new Status());
        if (selectedMahasiswa.getProgramStudi() == null) selectedMahasiswa.setProgramStudi(new ProgramStudi());
    }

    public void handleFileFoto(FileUploadEvent event) {
        System.out.println(event.getFile().getFileName());
    }

    public void handleUploadedFile() {
        if (uploadedFile != null) {
            String getSavePath = System.getProperty("sistime.image-resources");
            String fileName = uploadedFile.getFileName();
            String ekstensi = fileName.substring(fileName.lastIndexOf('.'), fileName.length());
            Timestamp timestamp = new Timestamp(new Date().getTime());
            String newFileName = String.format("%x_%s%s", timestamp.getTime(),
                    selectedMahasiswa.getNamaMahasiswa().replace(' ', '_'),
                    ekstensi);
            //selectedMahasiswa.setMahasiswaFiles(new HashSet<>());
            MahasiswaFile mahasiswaFile = new MahasiswaFile();
            mahasiswaFile.setMahasiswa(selectedMahasiswa);
            mahasiswaFile.setFile(newFileName);
            mahasiswaFile.setTipeFile("pasfoto");
            selectedMahasiswa.getMahasiswaFiles().add(mahasiswaFile);

            try {
                File _file = new File(getSavePath + File.separator + newFileName);
                OutputStream out = new FileOutputStream(_file);
                InputStream in;
                out.write(uploadedFile.getContent());
                out.flush();
                out.close();
                System.out.println(newFileName + " telah dicopy");
            } catch (Exception ex) {
                System.err.println("error: " + ex.getMessage());
            }
        }
    }

    public Mahasiswa getSelectedMahasiswa() {
        return selectedMahasiswa;
    }

    public void setSelectedMahasiswa(Mahasiswa selectedMahasiswa) {
        this.selectedMahasiswa = selectedMahasiswa;
    }

    public List<Mahasiswa> getMahasiswas() {
        return mahasiswas;
    }

    public void setMahasiswas(List<Mahasiswa> mahasiswas) {
        this.mahasiswas = mahasiswas;
    }

    public UploadedFile getUploadedFile() {
        return uploadedFile;
    }

    public void setUploadedFile(UploadedFile uploadedFile) {
        this.uploadedFile = uploadedFile;
    }

    public String get_idSekolah() {
        return _idSekolah;
    }

    public void set_idSekolah(String _idSekolah) {
        this._idSekolah = _idSekolah;
    }

    public void onRowSelect() {
        FacesContext.getCurrentInstance().addMessage("", new FacesMessage(FacesMessage.SEVERITY_INFO, selectedMahasiswa.getNamaMahasiswa(), ""));
    }

    public List<Mahasiswa> getFilteredMahasiswa() {
        return filteredMahasiswa;
    }

    public void setFilteredMahasiswa(List<Mahasiswa> filteredMahasiswa) {
        this.filteredMahasiswa = filteredMahasiswa;
    }
}
