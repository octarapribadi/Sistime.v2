package bean.validator;


import repo.MahasiswaManager;
import repo.RegistrasiManager;

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
public class EmailValidatorBean implements Validator {

    @EJB
    RegistrasiManager registrasiManager;
    @EJB
    MahasiswaManager mahasiswaManager;

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException { 
        if (registrasiManager.findEmail(value.toString())) {
            throw (new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Email " + value.toString() + " telah digunakan!",
                    "")));
        }

        if(mahasiswaManager.findEmail(value.toString())){
            throw (new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Email " + value.toString() + " telah digunakan!",
                    "")));
        }


    }
}
