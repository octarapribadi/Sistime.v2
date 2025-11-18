package bean;

import ejb.GambarProfilManager;
import ejb.StatusPendaftaranManager;
import model.GambarProfil;
import model.StatusPendaftaran;
import org.jboss.logging.Logger;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;

@Named
@ViewScoped
public class StatusPendaftaranBean implements Serializable {
    GambarProfil gambarProfil;

    @EJB
    StatusPendaftaranManager statusPendaftaranManager;

    @EJB
    GambarProfilManager gambarProfilManager;

    StatusPendaftaran selectedStatusPendaftaran;

    public void konfirmasi() {
        if (selectedStatusPendaftaran != null) {
            byte curr = selectedStatusPendaftaran.getStatus();
            curr++;
            selectedStatusPendaftaran.setStatus(curr);
            statusPendaftaranManager.merge(selectedStatusPendaftaran);
            FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_INFO,"Konfirmasi berhasil",""));
        }
    }

    public List<StatusPendaftaran> findAllStatusPendaftaranByStatus() {
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        if (ec.isUserInRole("administrator"))
            return statusPendaftaranManager.findAllStatusPendaftaran();
        else if (ec.isUserInRole("marketing"))
            return statusPendaftaranManager.findAllStatusPendaftaranByStatus(StatusPendaftaran.MARKETING);
        else if (ec.isUserInRole("keuangan"))
            return statusPendaftaranManager.findAllStatusPendaftaranByStatus(StatusPendaftaran.KEUANGAN);
        else if (ec.isUserInRole("prodi"))
            return statusPendaftaranManager.findAllStatusPendaftaranByStatus(StatusPendaftaran.PRODI);
        else return null;
    }

    public byte[] getPp(long idUser) {
        try {
            gambarProfil = gambarProfilManager.findGambarProfileById(idUser);
            if (gambarProfil == null || gambarProfil.getUrl() == null) {
                return FacesContext.getCurrentInstance().getExternalContext().getResourceAsStream("resources/images/no_profile.png").readAllBytes();
            } else {
                FileInputStream fis = new FileInputStream(gambarProfil.getUrl());
                byte[] pp = fis.readAllBytes();
                fis.close();
                return pp;
            }
        } catch (IOException ex) {
            Logger.getLogger(UploadDokumenBean.class).log(Logger.Level.ERROR, ex);
            return null;
        }
    }

    public String getStatusColor(StatusPendaftaran statusPendaftaran) {
        if (statusPendaftaran.getId() == StatusPendaftaran.MARKETING)
            return "text-green-200";
        else if (statusPendaftaran.getId() == StatusPendaftaran.KEUANGAN)
            return "text-green-400";
        else if (statusPendaftaran.getId() == StatusPendaftaran.PRODI)
            return "text-green-700";
        else if (statusPendaftaran.getId() == StatusPendaftaran.DONE)
            return "text-green-900";
        else return "text-red-900";
    }

    public StatusPendaftaran getSelectedStatusPendaftaran() {
        return selectedStatusPendaftaran;
    }

    public void setSelectedStatusPendaftaran(StatusPendaftaran selectedStatusPendaftaran) {
        this.selectedStatusPendaftaran = selectedStatusPendaftaran;
    }

    public GambarProfil getGambarProfil() {
        return gambarProfil;
    }

    public void setGambarProfil(GambarProfil gambarProfil) {
        this.gambarProfil = gambarProfil;
    }

}
