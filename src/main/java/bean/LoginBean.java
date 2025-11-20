package bean;

import repo.UserManager;
import model.User;
import org.jboss.logging.Logger;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;

@Named
@ViewScoped
public class LoginBean implements Serializable {
    String requestURI;
    String username;
    String password;
    String hakAkses;

    @Inject
    SessionBean sessionBean;

    @EJB
    UserManager userManager;

    public void init(){
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        String registrasiSukses = request.getParameter("registrasisukses")!=null?request.getParameter("registrasisukses"):null;
        if(registrasiSukses!=null && registrasiSukses.equals("true")){
            context.addMessage(null,new FacesMessage(FacesMessage.SEVERITY_INFO,"Registrasi berhasil","Silahkan konfirmasi pada email yang dikirim"));
        }
        //requestURI = (String)FacesContext.getCurrentInstance().getExternalContext().getRequestMap().get(RequestDispatcher.FORWARD_REQUEST_URI);
    }

    public String login() {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        try {
            request.login(username, password);
            User user = userManager.findUser(username);
            sessionBean.setUsername(username);
            sessionBean.setId(user.getId());
            //ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
            //String url = ec.getRequestContextPath() + "/" + hakAkses;
            //ec.redirect(url);
            Logger.getLogger(LoginBean.class).info(username+" melakukan login.");
            return "/" + hakAkses + "/index.xhtml?faces-redirect=true";
        } catch (ServletException e) {
            //throw new RuntimeException(e);
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Login Gagal!", "Username atau password salah!"));
            return null;
        }
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getHakAkses() {
        return hakAkses;
    }

    public void setHakAkses(String hakAkses) {
        this.hakAkses = hakAkses;
    }
}
