package bean;

import ejb.KrsMahasiswaManager;
import model.KrsMahasiswa;
import model.User;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.ServletRequest;
import java.io.Serializable;
import java.util.List;

@Named
@SessionScoped
public class LihatKrsMahasiswaBean implements Serializable {
    List<User> userList;
    List<KrsMahasiswa> krsList;
    Long krsId;
    User selectedUser;
    @EJB
    KrsMahasiswaManager krsMahasiswaManager;


    public void fillUserListByKrsId() {
        userList = krsMahasiswaManager.findAllKrsBySkemaKrs(krsId);
    }

    public void findAllKrsByUserIdAndKrsId(Long userId){
        krsList = krsMahasiswaManager.findAllKrsMahasiswaByUserAndKrsId(userId,krsId);
    }
    public void deleteKrsByUserIdAndKrsId(){
        List<KrsMahasiswa> krsMahasiswas = krsMahasiswaManager.findAllKrsMahasiswaByUserAndKrsId(selectedUser.getId(),krsId);
        if(krsMahasiswas!=null){
            krsMahasiswaManager.removeByList(krsMahasiswas);
            userList.remove(selectedUser);
            selectedUser = null;
            FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_INFO,"krs berhasil dihapus",""));
        }
    }

    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }

    public List<KrsMahasiswa> getKrsList() {
        return krsList;
    }

    public void setKrsList(List<KrsMahasiswa> krsList) {
        this.krsList = krsList;
    }

    public Long getKrsId() {
        return krsId;
    }

    public void setKrsId(Long krsId) {
        this.krsId = krsId;
    }

    public User getSelectedUser() {
        return selectedUser;
    }

    public void setSelectedUser(User selectedUser) {
        this.selectedUser = selectedUser;
    }
}
