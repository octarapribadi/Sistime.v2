package bean;

import repo.RegistrasiManager;
import model.Registrasi;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@ViewScoped
public class KonfirmasiPendaftaranBean implements Serializable {

    Registrasi selectedRegistrasi;
    List<Registrasi> registrasis;

    @EJB
    RegistrasiManager registrasiManager;

    @Inject
    ConfirmBean confirmBean;

    @PostConstruct
    private void init(){
        selectedRegistrasi = new Registrasi();
    }

    public void konfirmasi(){
        if(confirmBean.confirmByRegistrasi(selectedRegistrasi)){
            registrasis.remove(selectedRegistrasi);
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO,"Data berhasil ditambahkan",""));
        }
    }

    public void hapus(){
        registrasis.remove(selectedRegistrasi);
        registrasiManager.hapus(selectedRegistrasi);
    }

    public void findAllRegistrasi(){
        registrasis = registrasiManager.findAllRegistrasi();
    }

    public Registrasi getSelectedRegistrasi() {
        return selectedRegistrasi;
    }

    public void setSelectedRegistrasi(Registrasi selectedRegistrasi) {
        this.selectedRegistrasi = selectedRegistrasi;
    }

    public List<Registrasi> getRegistrasis() {
        return registrasis;
    }

    public void setRegistrasis(List<Registrasi> registrasis) {
        this.registrasis = registrasis;
    }
}
