package bean;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

@Named
@RequestScoped
public class AjaxBean implements Serializable {
    String val;
    String val2;

    public String getVal2() {
        return val2;
    }

    public void setVal2(String val2) {
        this.val2 = val2;
    }

    public String getVal() {
        return val;
    }

    public void setVal(String val) {
        this.val = val;
    }


    public void submit(){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-mm-dd HH:mm:ss");
        Date now = new Date();
        val = simpleDateFormat.format(now);
        FacesContext.getCurrentInstance()
                .addMessage(null,new FacesMessage(FacesMessage.SEVERITY_INFO,"Summary","Detail"));
    }

    public void submit2(){
        FacesContext.getCurrentInstance().addMessage(null,new FacesMessage("Hi, " + val+" "+val2));
    }

}
