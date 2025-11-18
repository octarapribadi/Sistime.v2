package bean;

import org.apache.commons.lang3.RandomStringUtils;
import org.jboss.logging.Logger;
import org.primefaces.model.file.UploadedFile;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;

@Named
@RequestScoped
public class FileUploadBean implements Serializable {

    public String upload(String path, UploadedFile file, Boolean rewriteIfExist) {
        if (file != null) {
            try {
                File dir = new File(path);
                String namaFile = file.getFileName();
                File f = new File(dir + File.separator + namaFile);

                if (!dir.exists())
                    dir.mkdirs();

                if (f.exists() && !rewriteIfExist) {
                    int idx = namaFile.lastIndexOf(".");
                    String prefix = namaFile.substring(0, idx);
                    String suffix = namaFile.substring(idx);
                    String random = RandomStringUtils.random(8, "abcdefghijklmnopqrstuvwxyz0123456789");
                    namaFile = prefix + "_" + random + suffix;
                }

                FileOutputStream out = new FileOutputStream(path + File.separator + namaFile);
                out.write(file.getContent());
                out.close();
                return namaFile;
            } catch (IOException ex) {
                Logger.getLogger(FileUploadBean.class).error(ex.getMessage());
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Berkas gagal diunggah", ex.getMessage()));
                return null;
            }
        } else
            return null;
    }

    public Boolean delete(File file){
        try{
            return file.delete();
        }
        catch(Exception ex){
            Logger.getLogger(FileUploadBean.class).error(ex.getMessage());
            return false;
        }
    }
}
