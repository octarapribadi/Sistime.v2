package bean.converter;

import model.KategoriPam;
import model.Pam;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Named;

@Named
@FacesConverter("statusPamConverter")
public class StatusPamConverter implements Converter<Integer> {

    @Override
    public Integer getAsObject(FacesContext context, UIComponent component, String value) {
        if(value.equals("Diproses"))
            return Pam.DIPROSES;
        else if(value.equals("Ditolak"))
            return Pam.DITOLAK;
        else
            return Pam.DITERIMA;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Integer value) {
        if(value == Pam.DIPROSES)
            return "Diproses";
        else if(value == Pam.DITOLAK)
            return "Ditolak";
        else return "Diterima";
    }
}
