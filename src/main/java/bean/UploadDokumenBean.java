package bean;

import ejb.DokumenManager;
import model.Dokumen;
import model.JenisDokumen;
import org.apache.commons.compress.utils.FileNameUtils;
import org.jboss.logging.Logger;
import org.primefaces.PrimeFaces;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.file.UploadedFile;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.*;
import java.util.List;

@Named
@ViewScoped
public class UploadDokumenBean implements Serializable {
    List<Dokumen> selectedDokumens;
    List<JenisDokumen> jenisDokumens;
    String fileDir;
    Dokumen selectedDokumen;
    UploadedFile uploadedFile;
    String status;
    @Inject
    bean.SessionBean sessionBean;

    @EJB
    DokumenManager dokumenManager;

    public List<Dokumen> findDokumenByUserId() {
        return dokumenManager.findDokumenByUserId(sessionBean.getId());
    }

    public void openNew() {
        status = "new";
        selectedDokumen = new Dokumen();
        selectedDokumen.setJenisDokumen(new JenisDokumen());
        if (jenisDokumens == null) {
            jenisDokumens = dokumenManager.findAllJenisDokumen();
        }
    }

    public void edit() {
        status = "edit";
        if (jenisDokumens == null) {
            jenisDokumens = dokumenManager.findAllJenisDokumen();
        }
    }

    public void delete() {
        File file;
        if (selectedDokumen != null) {
            //System.out.println("data delete:" + selectedDokumen.getId());
            dokumenManager.delete(selectedDokumen);
            file = new File(selectedDokumen.getUrl());
            if(file.exists()) file.delete();
        }
    }

    public void deletes() {
        File file;
        if (selectedDokumens != null && !selectedDokumens.isEmpty()) {
            for (Dokumen d : selectedDokumens) {
                dokumenManager.delete(d);
                file = new File(d.getUrl());
                if(file.exists()) file.delete();
            }
            selectedDokumens=null;
            PrimeFaces.current().ajax().update("form_dokumen:btn_delete","form_dokumen:dt_dokumen");
        }
    }

    public void persist() {
        if (fileHandler()) {
            selectedDokumen.setIdUser(sessionBean.getId());
            selectedDokumen.setUrl(fileDir);
            if (status == null) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"silahkan ulangi kembali",""));
            } else {
                if (status.equals("new")) {
                    dokumenManager.persist(selectedDokumen);
                    status = null;
                } else if (status.equals("edit")) {
                    dokumenManager.merge(selectedDokumen);
                    status = null;
                }

                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Data berhasil disimpan", uploadedFile.getFileName()));

            }
        }
    }

    public UploadedFile getUploadedFile() {
        return uploadedFile;
    }

    public StreamedContent download(String url) throws IOException {
        File file = new File(url);
        FileInputStream fis = new FileInputStream(file);
        return DefaultStreamedContent.builder().name(file.getName()).stream(()->fis).build();

    }

    private Boolean fileHandler() {
        try {
            String dir = FacesContext.getCurrentInstance().getExternalContext().getInitParameter("sistime.userdir");
            dir = String.format("%s%s%s", dir, File.separator, sessionBean.getId());
            File directory = new File(dir);
            if (!directory.exists()) {
                directory.mkdirs();
            }
            File file = new File(directory, uploadedFile.getFileName());
            this.fileDir = file.getAbsolutePath();
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            fileOutputStream.write(uploadedFile.getContent());
            fileOutputStream.flush();
            fileOutputStream.close();
            return true;
        } catch (SecurityException | IOException ex) {
            Logger.getLogger(UploadDokumenBean.class).log(Logger.Level.ERROR, ex);
            return false;
        }
    }

    public String getBtnDeleteValue() {
        if (selectedDokumens == null) {
            return "Hapus";
        } else {
            return selectedDokumens.isEmpty() ? "Hapus" : "Hapus " + selectedDokumens.size() + " row(s)";
        }
    }


    public void setUploadedFile(UploadedFile uploadedFile) {
        this.uploadedFile = uploadedFile;
    }

    public Dokumen getSelectedDokumen() {
        return selectedDokumen;
    }

    public void setSelectedDokumen(Dokumen selectedDokumen) {
        this.selectedDokumen = selectedDokumen;
    }

    public List<JenisDokumen> getJenisDokumens() {
        return jenisDokumens;
    }

    public void setJenisDokumens(List<JenisDokumen> jenisDokumens) {
        this.jenisDokumens = jenisDokumens;
    }

    public List<Dokumen> getSelectedDokumens() {
        return selectedDokumens;
    }

    public void setSelectedDokumens(List<Dokumen> selectedDokumens) {
        this.selectedDokumens = selectedDokumens;
    }

}
