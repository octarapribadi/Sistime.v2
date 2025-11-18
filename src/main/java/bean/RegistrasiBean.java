package bean;

import ejb.RegistrasiManager;
import ejb.StatusPendaftaranManager;
import model.Registrasi;
import model.StatusPendaftaran;
import org.jboss.logging.Logger;
import org.wildfly.security.password.PasswordFactory;
import org.wildfly.security.password.WildFlyElytronPasswordProvider;
import org.wildfly.security.password.interfaces.SimpleDigestPassword;
import org.wildfly.security.password.spec.ClearPasswordSpec;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.PersistenceException;
import javax.servlet.http.HttpSession;
import java.io.Serializable;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Base64;
import java.util.Date;
import java.util.Random;

@Named
@SessionScoped
public class RegistrasiBean implements Serializable {
    String nama;
    String username;
    String email;
    String password;
    String retypePassword;
    String hashCode;
    @EJB
    RegistrasiManager registrasiManager;
    
    @Inject
    SendMail sendMail;

    Registrasi registrasi;

    Logger logger = Logger.getLogger(RegistrasiBean.class);
    public String daftar(){
        try {
            registrasi=new Registrasi();
            registrasi.setTanggalPendaftaran(new Date());
            registrasi.setNamaMahasiswa(nama);
            registrasi.setEmail(email);
            registrasi.setVerifikasi(kodeVerifikasiGenerator());
            registrasi.setUsername(username);
            registrasi.setPassword(getHashCodeFromPassword(password));
            registrasiManager.simpan(registrasi);
            emailHandler();
            return "/mahasiswa/index.xhtml?faces-redirect=true&registrasisukses=true";
        }
        catch(Exception ex){
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null,new FacesMessage(FacesMessage.SEVERITY_ERROR,"error",ex.getMessage()));
            logger.log(Logger.Level.ERROR,"error:"+ex.getMessage());
            return "/registrasi.xhtml";
        }
    }

    private void emailHandler(){
        String path = System.getProperty("sistime.confirm-url");
        String template = String.format(
            "<h2>Selamat bergabung di STMIK Time</h2>"+
            "<h4>Berikut informasi user kamu</h4>"+
            "<hr>"+
            "<h4>Username: <span style=\"color: blue;\">%s</span></h4>"+
            "<h4>Nama: <span style=\"color:blue;\">%s</span></h4>" +
            "<h4>Data kamu akan diverifikasi terlebih dahulu oleh bagian marketing</h4>"
            ,
                registrasi.getUsername(),registrasi.getNamaMahasiswa(),path,registrasi.getVerifikasi()
        );

        sendMail.subject = "Konfirmasi Pendaftaran";
        sendMail.content = template;
        sendMail.toEmail = registrasi.getEmail();
        sendMail.sendMail();
    }

    private String kodeVerifikasiGenerator(){
        Random rnd = new Random();
        byte[] rndBytes = new byte[128];
        rnd.nextBytes(rndBytes);
        return Base64.getUrlEncoder().encodeToString(rndBytes);
        //registrasiMahasiswa.setKodeVerifikasi(kodeVerifikasi);
    }

    @PreDestroy
    private void preDestroy(){

        try{
            //System.out.println("RegistrasiBean preDestroy...");
            if(registrasi.getVerifikasi()!=null) {
                Registrasi registrasi = registrasiManager.findRegistrasiByVerification(this.registrasi.getVerifikasi());
                if (registrasi != null) {
                    registrasiManager.hapus(registrasi);
                }
            }
        }
        catch(PersistenceException ex){
            logger.log(Logger.Level.ERROR,"error:" + ex);
        }
    }

    @PostConstruct
    private void postConstruct(){
        //FacesContext context = FacesContext.getCurrentInstance();
        //HttpSession session = (HttpSession) context.getExternalContext().getSession(true);
        //session.setMaxInactiveInterval(60*5);
    }

    private String getHashCodeFromPassword(String password){
        try {
            PasswordFactory passwordFactory = PasswordFactory.getInstance(SimpleDigestPassword.ALGORITHM_SIMPLE_DIGEST_MD5, new WildFlyElytronPasswordProvider());
            ClearPasswordSpec clearPasswordSpec = new ClearPasswordSpec(password.toCharArray());
            SimpleDigestPassword simpleDigestPassword = (SimpleDigestPassword) passwordFactory.generatePassword(clearPasswordSpec);
            byte[] hash = simpleDigestPassword.getDigest();
            StringBuilder sb = new StringBuilder();
            for(byte b: hash){
                sb.append(String.format("%02X",b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            throw new RuntimeException(e);
        }
    }
    
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRetypePassword() {
        return retypePassword;
    }

    public void setRetypePassword(String retypePassword) {
        this.retypePassword = retypePassword;
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
