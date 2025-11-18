package bean;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;

@Named
@RequestScoped
public class RequestBean {
    String data;

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String setAction(){
        System.out.println("data:" + data);
        return "/Sistime/session_page2.xhtml?faces-redirect=true";
    }
}
