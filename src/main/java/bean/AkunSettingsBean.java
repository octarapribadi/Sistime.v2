package bean;


import repo.UserManager;
import model.User;
import org.apache.commons.codec.digest.DigestUtils;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;

@Named
@ViewScoped
public class AkunSettingsBean implements Serializable {
    User user;
    String password;
    String retypePassword;
    @Inject
    SessionBean session;
    @EJB
    UserManager userManager;

    public void initUser() {
        user = userManager.findUser(session.getUsername());
    }

    public void simpan() {
        if (password.equals(retypePassword)) {
            user.setPassword(DigestUtils.md5Hex(password.getBytes()).toUpperCase());
            userManager.merge(user);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "password berhasil dirubah", ""));
        }
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRetypePassword() {
        return retypePassword;
    }

    public void setRetypePassword(String retypePassword) {
        this.retypePassword = retypePassword;
    }
}
