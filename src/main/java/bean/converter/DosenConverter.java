package bean.converter;

import ejb.DosenManager;
import model.Dosen;

import javax.ejb.EJB;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Named;

@Named
@FacesConverter(value = "dosenConverter")
public class DosenConverter implements Converter<Dosen> {
    @EJB
    DosenManager dosenManager;

    @Override
    public Dosen getAsObject(FacesContext context, UIComponent component, String value) {
        System.out.println(value);
        if (value != null && !value.trim().isEmpty()) {
            return dosenManager.findDosenById(Long.parseLong(value));
        } else {
            return null;
        }

    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Dosen value) {
        if (value != null) {
            return String.valueOf(value.getNamaDosen());
        } else {
            return "";
        }
    }
}
