package bean;

import repo.*;
import model.*;
import org.jboss.logging.Logger;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.persistence.PersistenceException;
import javax.transaction.Transactional;
import java.io.Serializable;


@Named
@RequestScoped
public class ConfirmBean implements Serializable {
    @EJB MahasiswaManager mahasiswaManager;
    @EJB RegistrasiManager registrasiManager;
    @EJB UserManager userManager;
    @EJB RolesManager rolesManager;
    @EJB UserRoleManager userRoleManager;

    @EJB StatusPendaftaranManager statusPendaftaranManager;

    String verify;
    Logger logger = Logger.getLogger(ConfirmBean.class);

    public Boolean confirmByRegistrasi(Registrasi registrasi){
        try{
            User user = new User();
            //user.setId(registrasi.getId());
            user.setUsername(registrasi.getUsername());
            user.setPassword(registrasi.getPassword());
            userManager.persist(user);

            StatusPendaftaran statusPendaftaran = new StatusPendaftaran();
            statusPendaftaran.setStatus(StatusPendaftaran.DONE);
            statusPendaftaran.setUser(user);
            statusPendaftaranManager.persist(statusPendaftaran);

            UserRole userRole = new UserRole();
            userRole.setUser(user);
            userRole.setRole(rolesManager.findRolesIdByRole("mahasiswa"));
            userRoleManager.persist(userRole);

            Mahasiswa mahasiswa = new Mahasiswa();
            mahasiswa.setNamaMahasiswa(registrasi.getNamaMahasiswa());
            mahasiswa.setEmail(registrasi.getEmail());
            mahasiswa.setTanggalPendaftaran(registrasi.getTanggalPendaftaran());
            mahasiswa.setUser(user);
            mahasiswaManager.persist(mahasiswa);
            //fc.addMessage(null,new FacesMessage(FacesMessage.SEVERITY_INFO,"Registrasi berhasil",""));
            registrasiManager.hapus(registrasi);
            return true;
        }
        catch(Exception ex){
            Logger.getLogger(ConfirmBean.class).error(ex.getMessage());
            return false;
        }
    }

    @Transactional(rollbackOn = PersistenceException.class)
    public void confirm2(){
        FacesContext fc = FacesContext.getCurrentInstance();
            Registrasi registrasi = registrasiManager.findRegistrasiByVerification(verify);
            if (registrasi != null) {
                User user = new User();
                user.setUsername(registrasi.getUsername());
                user.setPassword(registrasi.getPassword());
                userManager.persist(user);

                handleStatusPendaftaran(user);

                UserRole userRole = new UserRole();
                userRole.setUser(user);
                userRole.setRole(rolesManager.findRolesIdByRole("mahasiswa"));
                userRoleManager.persist(userRole);

                Mahasiswa mahasiswa = new Mahasiswa();
                mahasiswa.setNamaMahasiswa(registrasi.getNamaMahasiswa());
                mahasiswa.setEmail(registrasi.getEmail());
                mahasiswa.setTanggalPendaftaran(registrasi.getTanggalPendaftaran());
                mahasiswa.setUser(user);
                mahasiswaManager.persist(mahasiswa);
                fc.addMessage(null,new FacesMessage(FacesMessage.SEVERITY_INFO,"Registrasi berhasil",""));
                registrasiManager.hapus(registrasi);
        }
    }
    private void handleStatusPendaftaran(User user){
        if(user!=null && user.getId()!=null){
            StatusPendaftaran statusPendaftaran = new StatusPendaftaran();
            statusPendaftaran.setStatus(StatusPendaftaran.MARKETING);
            statusPendaftaran.setUser(user);
            statusPendaftaranManager.persist(statusPendaftaran);
        }
    }

    public String getVerify() {
        return verify;
    }

    public void setVerify(String verify) {
        this.verify = verify;
    }
}
