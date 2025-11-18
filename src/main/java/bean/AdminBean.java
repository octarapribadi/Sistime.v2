package bean;

import org.jboss.logging.Logger;
import org.wildfly.security.auth.callback.CachedIdentityAuthorizeCallback;
import org.wildfly.security.auth.server.SecurityDomain;
import org.wildfly.security.auth.server.SecurityIdentity;
import org.wildfly.security.authz.Roles;
import org.wildfly.security.cache.CachedIdentity;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.net.http.HttpRequest;
import java.security.Principal;
import java.util.Map;

@Named
@RequestScoped
public class AdminBean implements Serializable {
    public void init(){
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        CachedIdentity ci = (CachedIdentity) FacesContext.getCurrentInstance()
                .getExternalContext().getSessionMap()
                .get("org.wildfly.security.http.HttpAuthenticator.authenticated-identity");
        if(ci!=null){
            SecurityIdentity si = ci.getSecurityIdentity();
            for(String s: si.getAttributes().keySet()){
                System.out.print(s+":");
                System.out.println(si.getAttributes().get(s));
            }
        }

    }
}
