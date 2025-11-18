package bean;

import org.wildfly.security.auth.server.SecurityDomain;
import org.wildfly.security.auth.server.SecurityIdentity;
import org.wildfly.security.authz.Roles;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;

@Named
@RequestScoped
public class ClearSession {
    String clear;

    public String getClear() {
        return clear;
    }

    public void setClear(String clear) {
        this.clear = clear;
    }

    public void execute(){
        if(clear.equals("true")){
            FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        }
        //else{
            //SecurityDomain currSecDomain = SecurityDomain.getCurrent();
            //SecurityIdentity currSecIdentity = currSecDomain.getCurrentSecurityIdentity();
            //System.out.println(currSecIdentity.getPrincipal().getName());

        //}
    }
}
