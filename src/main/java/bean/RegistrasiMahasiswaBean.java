package bean;

import ejb.*;
import model.*;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.FlowEvent;
import org.primefaces.model.file.UploadedFile;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.PersistenceException;
import java.io.*;
import java.sql.Timestamp;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.Random;

@Named("regBean")
@ViewScoped
public class RegistrasiMahasiswaBean implements Serializable {

    RegistrasiMahasiswa registrasiMahasiswa;
    ProgramStudi programStudi;
    Status status;
    Agama agama;
    Kampus kampus;
    WaktuKuliah waktuKuliah;
    String _idSekolah;
    Boolean skip;
    UploadedFile file;
    String tempFileName;
    List<WaktuKuliah> waktuKuliahList;

    @EJB
    StatusManager statusManager;

    @EJB
    RegistrasiMahasiswaManager registrasiMahasiswaManager;

    @EJB
    SekolahManager sekolahManager;

    @EJB
    ProgramStudiManager programStudiManager;

    @EJB
    AgamaManager agamaManager;

    @EJB
    WaktuKuliahManager waktuKuliahManager;

    @EJB
    KampusManager kampusManager;

    @Inject
    SendMail sendMail;

    @PostConstruct
    public void init() {
        //System.out.println(FacesContext.getCurrentInstance().getCurrentPhaseId().toString());
        registrasiMahasiswa = new RegistrasiMahasiswa();
        programStudi = new ProgramStudi();
        status = new Status();
        agama = new Agama();
        kampus = new Kampus();
        waktuKuliah = new WaktuKuliah();

        registrasiMahasiswa.setProgramStudi(programStudi);
        registrasiMahasiswa.setStatus(status);
        registrasiMahasiswa.setAgama(agama);
        registrasiMahasiswa.setKampus(kampus);
        registrasiMahasiswa.setWaktuKuliah(waktuKuliah);

        System.out.println(Integer.toHexString(System.identityHashCode(this))+" is constructed..");
        //System.out.println(FacesContext.getCurrentInstance().getExternalContext().getInitParameter("primefaces.PRIVATE_CAPTCHA_KEY"));
        //System.out.println(System.getProperty("sistime.image-resources"));

        //System.out.println(FacesContext.getCurrentInstance().getExternalContext().getInitParameter("primefaces.PUBLIC_CAPTCHA_KEY"));

    }

    @PreDestroy
    public void predestroy(){
        if(file!=null){
            String getPath = System.getProperty("sistime.image-resources");
            File tempFile = new File(getPath + File.separator + tempFileName);
            if(tempFile.exists()) {
                if(tempFile.delete())System.out.println(tempFileName+" telah dihapus!") ;
            }
        }
        try{
            //System.out.println(registrasiMahasiswa.getIdPendaftaran());
            registrasiMahasiswaManager.hapus(registrasiMahasiswa);
        }
        catch(PersistenceException exception){
            System.err.println(exception.getMessage());
        }
        System.out.println(Integer.toHexString(System.identityHashCode(this))+" is destroyed..");
    }

    public List<WaktuKuliah> getWaktuKuliahList() {
        return waktuKuliahList;
    }

    public void setWaktuKuliahList(List<WaktuKuliah> waktuKuliahList) {
        this.waktuKuliahList = waktuKuliahList;
    }

    public UploadedFile getFile() {
        return file;
    }

    public void setFile(UploadedFile file) {
        this.file = file;
    }

    public Agama getAgama() {
        return agama;
    }

    public void setAgama(Agama agama) {
        this.agama = agama;
    }

    public String get_idSekolah() {
        return _idSekolah;
    }

    public void set_idSekolah(String _idSekolah) {
        this._idSekolah = _idSekolah;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public RegistrasiMahasiswa getRegistrasiMahasiswa() {
        return registrasiMahasiswa;
    }

    public void setRegistrasiMahasiswa(RegistrasiMahasiswa registrasiMahasiswa) {
        this.registrasiMahasiswa = registrasiMahasiswa;
    }

    public ProgramStudi getProgramStudi() {
        return programStudi;
    }

    public void setProgramStudi(ProgramStudi programStudi) {
        this.programStudi = programStudi;
    }

    public Boolean getSkip() {
        return skip;
    }

    public void setSkip(Boolean skip) {
        this.skip = skip;
    }

    public String onFlowProcess(FlowEvent event) {
        if (event.getNewStep().equals("tab_konfirmasi")) {
            agama = agamaManager.findAgamaById(agama.getIdAgama()) != null ? agamaManager.findAgamaById(agama.getIdAgama()) : null;
            programStudi = programStudiManager.findProgramStudiById(programStudi.getKodeProgramstudi());
            status = statusManager.findStatusById(status.getIdStatus()) != null ? statusManager.findStatusById(status.getIdStatus()) : null;
            return "tab_konfirmasi";
        } else {
            return event.getNewStep();
        }
    }

    public void submit() {
        try {
            //set tanggal pendaftaran
            registrasiMahasiswa.setTanggalPendaftaran(new Date());

            //set sekolah
            Sekolah sekolah = new Sekolah();
            sekolah.setIdSekolah(sekolahManager.findIdSekolahByNamaSekolah(_idSekolah));
            registrasiMahasiswa.setSekolah(sekolah);

            //set kode verifikasi
            Random rnd = new Random();
            byte[] rndBytes = new byte[128];
            rnd.nextBytes(rndBytes);
            String kodeVerifikasi = Base64.getUrlEncoder().encodeToString(rndBytes);
            registrasiMahasiswa.setKodeVerifikasi(kodeVerifikasi);

            //kirim kode verifikasi ke email
            handleEmail();

            //handle foto
            if(file!=null){
                Timestamp timestamp = new Timestamp(new Date().getTime());
                String namaFoto = String.format("%x_%s",timestamp.getTime(),tempFileName);
                namaFoto = namaFoto.replace("_temp_","");
                registrasiMahasiswa.setFileFoto(namaFoto);
                handleFileFoto(namaFoto);
            }

            //persist registrasiMahasiswa
            registrasiMahasiswaManager.simpan(registrasiMahasiswa);

            //debug
            debugRegistrasiMahasiswa();

            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "RegistrasiManager Berhasil",
                            "Link Konfirmasi telah dikirimkan ke email " + registrasiMahasiswa.getEmail()));

        } catch (Exception ex) {
            //ex.printStackTrace();
            System.out.println(ex.getMessage());
            FacesContext.getCurrentInstance()
                    .addMessage("", new FacesMessage(FacesMessage.SEVERITY_ERROR
                            , "Terjadi kesalahan silahkan hubungi pihak administrator kampus!"
                            , ex.getMessage()));
        }
    }

    private void handleFileFoto(String namaFoto) {
        String getPath = System.getProperty("sistime.image-resources");
        File tempFile = new File(getPath + File.separator + tempFileName);
        File newFile = new File(getPath+File.separator+namaFoto);
        //String newFileName = tempFileName.replace("_temp_", "");
        //File newFile = new File(getPath + File.separator + newFileName);

        tempFile.renameTo(newFile);

        //FacesMessage message = new FacesMessage("sukses",file.getFileName()+" is uploaded");
        //FacesContext.getCurrentInstance().addMessage(null,message);
    }

    private void handleEmail() {
        String path = System.getProperty("sistime.confirm-url");
        String template = String.format("Silahkan klik link disamping untuk mengkonfirmasi pendaftaran: %1s%2s"
                , path, registrasiMahasiswa.getKodeVerifikasi());
        sendMail.subject = "Konfirmasi Pendaftaran";
        sendMail.content = template;
        sendMail.toEmail = registrasiMahasiswa.getEmail();
        sendMail.sendMail();
    }

    public void handleFileFotoTemp(FileUploadEvent event) {

        file = event.getFile();
        String getPath = System.getProperty("sistime.image-resources");
        String namaFile = file.getFileName();
        String ekstensi = namaFile.substring(namaFile.lastIndexOf('.'), namaFile.length());
        tempFileName = "_temp_"
                + registrasiMahasiswa.getNamaMahasiswa().replace(' ', '_')
                + ekstensi;


        File _file = new File(getPath + File.separator + tempFileName);
        OutputStream out;
        InputStream in;
        try {
            out = new FileOutputStream(_file);
            out.write(file.getContent());
            out.flush();
            out.close();
            System.out.println(tempFileName + " telah dicopy");
        } catch (IOException ex) {
            ex.getMessage();
        }


    }

    private void debugRegistrasiMahasiswa() {
        System.out.println("Id Pendaftaran:" + registrasiMahasiswa.getIdPendaftaran());
        System.out.println("Tanggal Pendaftaran:" + registrasiMahasiswa.getTanggalPendaftaran());
        System.out.println("Nama:" + registrasiMahasiswa.getNamaMahasiswa());
        System.out.println("Email:" + registrasiMahasiswa.getEmail());
        System.out.println("Program Studi:" + registrasiMahasiswa.getProgramStudi().getKodeProgramstudi());
        System.out.println("Tempat Lahir:" + registrasiMahasiswa.getTempatLahir());
        System.out.println("Tanggal Lahir:" + registrasiMahasiswa.getTanggalLahir());
        System.out.println("Alamat:" + registrasiMahasiswa.getAlamatMahasiswa());
        System.out.println("JK:" + registrasiMahasiswa.getJenisKelamin());
        System.out.println("Agama:" + registrasiMahasiswa.getAgama().getIdAgama());
        System.out.println("Status:" + registrasiMahasiswa.getStatus().getStatus());
        System.out.println("Golongan Darah:" + registrasiMahasiswa.getGolonganDarah());
        System.out.println("Telepon:" + registrasiMahasiswa.getNoTeleponMahasiswa());
        System.out.println("Jlh Saudara:" + registrasiMahasiswa.getJumlahSaudara());
        System.out.println("Anak ke:" + registrasiMahasiswa.getAnakKe());
        System.out.println("Hobi:" + registrasiMahasiswa.getHobi());
        System.out.println("Ayah:" + registrasiMahasiswa.getNamaAyah());
        System.out.println("Ibu:" + registrasiMahasiswa.getNamaIbu());
        System.out.println("Alamat O.T.:" + registrasiMahasiswa.getAlamatOrangtua());
        System.out.println("Pekerjaan O.T.:" + registrasiMahasiswa.getPekerjaanOrangtua());
        System.out.println("Telepon O.T.:" + registrasiMahasiswa.getNoTeleponOrangtua());
        System.out.println("Nama Sekolah:" + _idSekolah);
        System.out.println("Kode Sekolah:" + registrasiMahasiswa.getSekolah().getIdSekolah());
        System.out.println("Kode Verifikasi:" + registrasiMahasiswa.getKodeVerifikasi());
    }

    public void getWaktuKuliahByKampus() {
        //System.out.println("toeng.." + kodeKampus);
        String kodeKampus = registrasiMahasiswa.getKampus().getKodeKampus();
        if (kodeKampus!= null && !kodeKampus.equals("")) {
            //System.out.println("true");
            waktuKuliahList = waktuKuliahManager.findWaktuKuliahByKodeKampus(kodeKampus);
        }
    }

}