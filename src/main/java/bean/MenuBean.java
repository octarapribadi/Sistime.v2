package bean;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import java.io.Serializable;

@Named
@RequestScoped
public class MenuBean implements Serializable {
    public String gotoMahasiswa(){
        return "mahasiswa";
    }
}
