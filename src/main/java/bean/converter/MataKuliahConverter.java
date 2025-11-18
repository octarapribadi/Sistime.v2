package bean.converter;

import ejb.MataKuliahManager;

import javax.ejb.EJB;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Named;

@Named
@FacesConverter(value = "mataKuliahConverter")
public class MataKuliahConverter implements Converter<Long> {

    @EJB
    MataKuliahManager matkulManager;

    @Override
    public Long getAsObject(FacesContext context, UIComponent component, String value) {
        System.out.println(value);
        return null;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Long value) {
        System.out.println(value);
        return null;
    }
}
