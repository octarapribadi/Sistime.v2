package bean.validator;

import ejb.SettingsManager;
import org.jboss.logging.Logger;

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
public class KrsValidator implements Validator<String> {

    @EJB
    SettingsManager settingsManager;

    @Override
    public void validate(FacesContext context, UIComponent component, String value) throws ValidatorException {
        try {
            int sks_max = Integer.parseInt(settingsManager.findSetting("sks_max").getValue());
            if (Integer.parseInt(value) > sks_max) {
                throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_INFO, "jumlah sks melebihi " + sks_max, ""));
            }
        } catch (Exception ex) {
            Logger.getLogger(KrsValidator.class).log(Logger.Level.ERROR, ex.getMessage());
        }
    }
}
