package bean;

import ejb.LupaPasswordManager;
import ejb.MahasiswaManager;
import ejb.UserManager;
import model.LupaPassword;
import model.User;
import org.jboss.logging.Logger;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.Base64;
import java.util.Random;

@Named
@ViewScoped
public class LupaPasswordBean implements Serializable {
    String email;
    @EJB
    MahasiswaManager mahasiswaManager;

    @Inject
    SendMail sendMail;

    String confirmUrl;

    @EJB
    LupaPasswordManager lupaPasswordManager;

    @EJB
    UserManager userManager;


    public void submit() {
        if (mahasiswaManager.findEmail(email)) {
            try {
                Long userId = mahasiswaManager.findUserIdByEmail(email);
                String confirmUrl = kodeVerifikasiGenerator();

                User user = new User();
                user.setId(userId);

                LupaPassword lupaPassword = new LupaPassword();
                lupaPassword.setUser(user);
                lupaPassword.setConfirmUrl(confirmUrl);
                lupaPasswordManager.persist(lupaPassword);

                sendMail.subject = "reset password";
                //sendMail.content = "http://www.stmik-time.ac.id/resetpassword.xhtml?confirmurl=" + confirmUrl;
                sendMail.content = contentString(confirmUrl);
                sendMail.toEmail = email;
                sendMail.sendMail();

                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_INFO,
                                "link reset password telah dikirim ke email " + email, ""));
            } catch (Exception ex) {
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_ERROR,
                                ex.getMessage(), ""));
                Logger.getLogger(LupaPasswordBean.class).error(ex.getMessage());
            }
        } else {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "email " + email + " tidak ditemukan", ""));
        }
    }

    public void confirm() {
        if (lupaPasswordManager.confirm(confirmUrl)) {
            Long userId = lupaPasswordManager.findUserIdByconfirmUrl(confirmUrl);
            User user = userManager.findUserByUserId(userId);
            if (user != null) {
                user.setPassword("912EC803B2CE49E4A541068D495AB570");
                userManager.merge(user);
            }
        }
    }

    private String kodeVerifikasiGenerator() {
        Random rnd = new Random();
        byte[] rndBytes = new byte[128];
        rnd.nextBytes(rndBytes);
        return Base64.getUrlEncoder().encodeToString(rndBytes);
    }

    public String contentString(String confirmUrl) {
        StringBuilder sb = new StringBuilder();
        sb.append("<p>Email ini berisi link untuk mereset password aplikasi Sistime</p>");
        sb.append("<p>Tekan link ");
        sb.append("<a href=\"")
                .append(System.getProperty("sistime.confirm-url"))
                .append(confirmUrl)
                .append("\">RESET</a>");
        sb.append(" untuk mereset password kamu menjadi 'asdf' tanpa tanda petik</p>");
        sb.append("<p>Abaikan email ini apabila kamu tidak ingin mereset password</p>");
        return sb.toString();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getConfirmUrl() {
        return confirmUrl;
    }

    public void setConfirmUrl(String confirmUrl) {
        this.confirmUrl = confirmUrl;
    }
}
