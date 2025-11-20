package bean;

import repo.GambarProfilManager;
import model.GambarProfil;
import org.jboss.logging.Logger;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.file.UploadedFile;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.*;

@Named
@ViewScoped
public class GambarProfilBean implements Serializable {
    byte[] gambarProfilBuff;

    GambarProfil gambarProfil;
    UploadedFile uploadedFile;
    @Inject
    SessionBean session;

    @EJB
    GambarProfilManager gambarProfilManager;

    @PostConstruct
    public void init() {
        gambarProfil = new GambarProfil();
        gambarProfilBuff = new byte[1];
    }

    public void findPpUser(Long idUser) {
        try {
            GambarProfil gp = gambarProfilManager.findGambarProfileById(idUser);
            if (gp == null || gp.getUrl().isEmpty()) {
                byte[] buff = FacesContext.getCurrentInstance().getExternalContext().getResourceAsStream("/resources/images/no_profile.png").readAllBytes();
                gambarProfilBuff = buff;
            } else {
                FileInputStream fis = new FileInputStream(gp.getUrl());
                gambarProfilBuff = fis.readAllBytes();
                fis.close();
            }
        } catch (IOException ex) {
            Logger.getLogger(UploadDokumenBean.class).log(Logger.Level.ERROR, ex);
        }
    }

    public byte[] getPp() {
        try {
            gambarProfil = gambarProfilManager.findGambarProfileById(session.getId());
            if (gambarProfil == null || gambarProfil.getUrl().isEmpty()) {
                return FacesContext.getCurrentInstance().getExternalContext().getResourceAsStream("/resources/images/no_profile.png").readAllBytes();
            } else {
                File file = new File(gambarProfil.getUrl());
                if (!file.exists())
                    return FacesContext.getCurrentInstance().getExternalContext().getResourceAsStream("/resources/images/no_profile.png").readAllBytes();
                else {
                    FileInputStream fis = new FileInputStream(file);
                    byte[] pp = fis.readAllBytes();
                    fis.close();
                    return pp;
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(UploadDokumenBean.class).log(Logger.Level.ERROR, ex);
            return null;
        }
    }

    public void handleGambarProfil(FileUploadEvent event) {
        uploadedFile = event.getFile();
        if (uploadedFile != null) {
            gambarProfil = gambarProfilManager.findGambarProfileById(session.getId());
            if (gambarProfil == null) {
                gambarProfil = new GambarProfil();
                gambarProfil.setIdUser(session.getId());
                gambarProfil.setUrl(fileHandler());
                gambarProfilManager.persist(gambarProfil);
            } else {
                File file = new File(gambarProfil.getUrl());
                file.delete();
                gambarProfil.setUrl(fileHandler());
                gambarProfilManager.merge(gambarProfil);
            }
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "gambar profil berhasil dirubah!", ""));
        }
    }

    private String fileHandler() {

        try {
            String dir = FacesContext.getCurrentInstance().getExternalContext().getInitParameter("sistime.userdir");

            File directory = new File(dir + File.separator + session.getId());
            if (!directory.exists()) {
                directory.mkdirs();
            }
            File file = new File(directory, uploadedFile.getFileName());
            FileOutputStream fos = new FileOutputStream(file);
            fos.write(uploadedFile.getContent());
            fos.flush();
            fos.close();
            return file.getAbsolutePath();
        } catch (Exception ex) {
            Logger.getLogger(UploadDokumenBean.class).log(Logger.Level.ERROR, ex);
            return null;
        }
    }

    public UploadedFile getUploadedFile() {
        return uploadedFile;
    }

    public void setUploadedFile(UploadedFile uploadedFile) {
        this.uploadedFile = uploadedFile;
    }

    public GambarProfil getGambarProfil() {
        return gambarProfil;
    }

    public void setGambarProfil(GambarProfil gambarProfil) {
        this.gambarProfil = gambarProfil;
    }

    public byte[] getGambarProfilBuff() {
        return gambarProfilBuff;
    }

    public void setGambarProfilBuff(byte[] gambarProfilBuff) {
        this.gambarProfilBuff = gambarProfilBuff;
    }
}
