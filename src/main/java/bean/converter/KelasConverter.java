package bean.converter;

import ejb.KelasManager;
import model.Kelas;

import javax.ejb.EJB;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Named;

@Named
@FacesConverter("kelasConverter")
public class KelasConverter implements Converter<Kelas> {

    @EJB
    KelasManager kelasManager;

    @Override
    public Kelas getAsObject(FacesContext context, UIComponent component, String value) {
        if (value != null && !value.trim().isEmpty()) {
            return kelasManager.findKelasByKodeKelas(value);
        } else return null;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Kelas value) {
        if (value != null) {
            return String.valueOf(value.getKodeKelas());
        } else return "";
    }
}
