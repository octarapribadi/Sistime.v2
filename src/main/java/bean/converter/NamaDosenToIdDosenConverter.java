package bean.converter;

import model.Dosen;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Named;

@Named
@FacesConverter(value="namaDosenToIdDosenConverter")
public class NamaDosenToIdDosenConverter implements Converter<Dosen> {

    @Override
    public Dosen getAsObject(FacesContext context, UIComponent component, String value) {
        return null;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Dosen value) {
        if(value!=null){
            return value.getNamaDosen();
        }
        else{
            return "";
        }
    }
}
