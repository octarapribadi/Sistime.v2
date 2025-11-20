package bean.validator;

import repo.StatusMahasiswaManager;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import javax.inject.Named;

@Named
@RequestScoped
public class NimValidator implements Validator<String> {
    @EJB
    StatusMahasiswaManager statusMahasiswaManager;
    @Override
    public void validate(FacesContext facesContext, UIComponent uiComponent, String s) throws ValidatorException {
        if(statusMahasiswaManager.checkNimExist(s)){
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_INFO, "nim sudah digunakan!",""));
        }
    }
}
