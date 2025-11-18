package bean;

import ejb.UserManager;
import model.User;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.io.Serializable;

@Named
@ViewScoped
public class UserBean implements Serializable {
    @EJB
    UserManager userManager;

    User selectedUser;

    @PostConstruct
    public void init(){
        selectedUser = new User();
    }

    public User getUserProfileById(Long id){
        if(id != null)
            return userManager.findUserByUserId(id);
        else
            return null;
    }

    public void save(){
        if(!selectedUser.getUsername().isEmpty()){
            User user = userManager.findUser(selectedUser.getUsername());
            if(user==null){
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"username tidak ditemukan!",""));
            }
            else {
                user.setPassword("912EC803B2CE49E4A541068D495AB570");
                userManager.merge(user);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "password "+selectedUser.getUsername()+" direset menjadi asdf", ""));
            }
        }
        else{
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"masukan username terlebih dahulu",""));
        }
    }

    public User getSelectedUser() {
        return selectedUser;
    }

    public void setSelectedUser(User selectedUser) {
        this.selectedUser = selectedUser;
    }
}
