package bean;

import repo.StatusManager;
import model.Status;

import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@ViewScoped
public class StatusBean implements Serializable {
    Status status;

    @EJB
    StatusManager statusManager;

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public List<Status> listStatus() {
        return statusManager.findAllStatus();
    }

    public List<Status> findAllStatus(){
        return statusManager.findAllStatus();
    }
}
