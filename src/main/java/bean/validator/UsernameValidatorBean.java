package bean.validator;


import repo.UserManager;
import model.User;

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
public class UsernameValidatorBean implements Validator {

    @EJB
    UserManager userManager;

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        String username = value.toString();
        if(!Character.isLetter(username.charAt(0))){
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR,"format username salah","karakter pertama harus berupa huruf"));
        }
        for(int i=1;i<username.length();i++){
            if(!Character.isLetterOrDigit(username.charAt(i))){
                throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR,"format username salah","gunakan kombinasi huruf atau angka tanpa simbol termasuk spasi"));
            }
        }
        User user = userManager.findUser(value.toString());
        if (user != null) {
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Username " + value + " telah digunakan",
                    "Pilih username lain"));
        }
    }
}
