package bean.converter;

import repo.SekolahManager;
import model.Sekolah;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;
import javax.inject.Named;

@Named
@FacesConverter(value = "sekolahConverter",managed = true)
public class SekolahConverter implements Converter<Sekolah> {

    @EJB
    SekolahManager sekolahManager;

    @Override
    public Sekolah getAsObject(FacesContext context, UIComponent component, String value) {
        if (value != null && !value.trim().isEmpty()) {
            try {
                return sekolahManager.findSekolahByNama(value);
            } catch (NumberFormatException e) {
                throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Conversion Error", ""));
            }
        } else {
            return null;
        }
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Sekolah value) {
        if (value != null) {
            return String.valueOf(value.getNamaSekolah());
        } else {
            return null;
        }
    }
}
