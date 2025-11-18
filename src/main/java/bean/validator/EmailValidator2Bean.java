package bean.validator;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import javax.inject.Named;

@Named("emailValidatorBeanV2")
@RequestScoped
public class EmailValidator2Bean implements Validator {
    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        String emailValidatorRegex = "^[_a-zA-Z0-9-]+(\\.[_a-zA-Z0-9-]+)*@[a-zA-Z0-9-]+"
                + "(\\.[a-zA-Z0-9-]+)*(\\.[a-zA-Z]{2,})$";
        if (value != null)
            if (!value.toString().matches(emailValidatorRegex))
                throw (new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR,
                        "Format email salah!",
                        null)));
    }
}
