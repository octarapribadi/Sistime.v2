package bean;

import repo.PamManager;
import model.KategoriPam;
import model.Pam;
import model.User;
import org.apache.commons.io.FileUtils;
import org.jboss.logging.Logger;
import org.primefaces.model.file.UploadedFile;
import org.primefaces.model.file.UploadedFileWrapper;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;

@Named
@ViewScoped
public class PamBean implements Serializable {
    List<Pam> pams;
    Pam selectedPam;
    Boolean berkasRequired;
    @EJB
    PamManager pamManager;
    String status;

    String mediaUrl;

    @Inject
    MediaBean mediaBean;

    UploadedFile dokumen;

    @Inject
    FileUploadBean fileUploadBean;

    @Inject
    SessionBean sessionBean;

    @Inject
    KategoriPamBean kategoriPamBean;

    @PostConstruct
    private void init() {
        selectedPam = new Pam();
        selectedPam.setKategoriPam(new KategoriPam());
        selectedPam.setUser(new User());
    }

    public void dialogClose(){
        String appPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath("");
        File file = new File(appPath+mediaUrl);
        if(file.exists())
            file.delete();
    }

    public void openNew() {
        status = "new";
        berkasRequired = true;
        resetSelectedPam();
    }

    public void edit() {
        status = "edit";
        berkasRequired = false;
    }

    public void delete() {
        ExternalContext ctx = FacesContext.getCurrentInstance().getExternalContext();
        String userdir = ctx.getInitParameter("sistime.userdir");
        String pamdir = ctx.getInitParameter("sistime.pamdir");
        String path = userdir + File.separator + sessionBean.getId() + File.separator + pamdir;
        try {
            File file = new File(path + File.separator + selectedPam.getBerkas());
            if (file.exists())
                file.delete();
            pams.remove(selectedPam);
            pamManager.remove(selectedPam);
            resetSelectedPam();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                    "Data berhasil dihapus", ""));
        } catch (Exception ex) {
            Logger.getLogger(PamBean.class).error(ex.getMessage());
        }
    }

    public void initMediaUrl() {
        if (selectedPam.getBerkas() != null) {
            String appPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath("");
            String userPamPath = getUserPamPath(String.valueOf(selectedPam.getUser().getId()));
            String pamdir = FacesContext.getCurrentInstance().getExternalContext().getInitParameter("sistime.pamdir");
            String tmpMediaCtxPath = File.separator + "tmp" + File.separator + pamdir + File.separator + selectedPam.getUser().getId();
            File src, dst, tmpDir;
            src = new File(userPamPath + File.separator + selectedPam.getBerkas());
            if (src.exists()) {
                try {
                    tmpDir = new File(tmpMediaCtxPath);
                    if (!tmpDir.exists())
                        tmpDir.mkdir();
                    dst = new File(appPath + tmpMediaCtxPath + File.separator + selectedPam.getBerkas());
                    if (!dst.exists())
                        FileUtils.copyFile(src, dst);
                    mediaUrl = tmpMediaCtxPath + File.separator + dst.getName();
                } catch (IOException e) {
                    Logger.getLogger(PamBean.class).error(e.getMessage());
                }
            }
        }
    }

    public void simpan() {
        String path = getUserPamPath(String.valueOf(sessionBean.getId()));
        if (status.equals("new")) {
            try {
                String fileName = fileUploadBean.upload(path, dokumen, false);
                selectedPam.getUser().setId(sessionBean.getId());
                selectedPam.setStatus(0);
                selectedPam.setBerkas(fileName);
                pamManager.persist(selectedPam);
                pams.add(selectedPam);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Pam berhasil ditambahkan"));
            } catch (Exception ex) {
                Logger.getLogger(PamBean.class).error(ex.getMessage());
            }
        } else if (status.equals("edit")) {
            try {
                File oldFile = new File(path + File.separator + selectedPam.getBerkas());
                if (oldFile.exists()) {
                    if (dokumen == null) {
                        dokumen = new UploadedFileWrapper();
                    } else {
                        String fileName = fileUploadBean.upload(path, dokumen, false);
                        selectedPam.setBerkas(fileName);
                        fileUploadBean.delete(oldFile);
                    }
                    selectedPam.setStatus(0);
                    pamManager.merge(selectedPam);
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Pam berhasil diubah"));
                }
            } catch (Exception ex) {
                Logger.getLogger(PamBean.class).error(ex.getMessage());
            }
        }
        resetSelectedPam();
        dokumen = null;
    }


    public void verify() {
        pamManager.merge(selectedPam);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Pam berhasil diverifikasi"));
        resetSelectedPam();
    }

    public void updateKategoriPamPoin() {
        selectedPam.setPoin(kategoriPamBean.findPoinByIdKategori(selectedPam.getKategoriPam().getId()));
    }

    public int sumTotalPoin() {
        int total = 0;
        if (pams != null) {
            for (Pam pam : pams) {
                if (pam.getStatus() == Pam.DITERIMA) {
                    total += pam.getPoin();
                }
            }
        }
        return total;
    }

    public void resetSelectedPam() {
        selectedPam = new Pam();
        selectedPam.setKategoriPam(new KategoriPam());
        selectedPam.setUser(new User());
    }

    public List<Pam> findAllPamByUserId(Long userId) {
        pams = pamManager.findAllPamByUserId(userId);
        return pams;
    }

    private String getUserPamPath(String userId) {
        ExternalContext ctx = FacesContext.getCurrentInstance().getExternalContext();
        String userdir = ctx.getInitParameter("sistime.userdir");
        String pamdir = ctx.getInitParameter("sistime.pamdir");
        String dir = userdir + File.separator + userId + File.separator + pamdir;
        return dir;
    }

    public List<Pam> findAllPam() {
        pams = pamManager.findAllPam();
        return pams;
    }

    public List<Pam> getPams() {
        return pams;
    }

    public void setPams(List<Pam> pams) {
        this.pams = pams;
    }

    public Pam getSelectedPam() {
        return selectedPam;
    }

    public void setSelectedPam(Pam selectedPam) {
        this.selectedPam = selectedPam;
    }

    public UploadedFile getDokumen() {
        return dokumen;
    }

    public void setDokumen(UploadedFile dokumen) {
        this.dokumen = dokumen;
    }

    public Boolean getBerkasRequired() {
        return berkasRequired;
    }

    public void setBerkasRequired(Boolean berkasRequired) {
        this.berkasRequired = berkasRequired;
    }

    public String getMediaUrl() {
        return mediaUrl;
    }

    public void setMediaUrl(String mediaUrl) {
        this.mediaUrl = mediaUrl;
    }
}
