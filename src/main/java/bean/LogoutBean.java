package bean;

import org.wildfly.security.http.HttpServerRequest;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Named
@RequestScoped
public class LogoutBean {
    public String logout(){
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        try {
            /*String requestURI = (String)FacesContext.getCurrentInstance()
                    .getExternalContext().getRequestMap()
                    .get(RequestDispatcher.FORWARD_REQUEST_URI);
             */
            request.logout();
            return "/index.xhtml?faces-redirect=true";
        } catch (ServletException e) {
            throw new RuntimeException(e);
        }
    }
}
