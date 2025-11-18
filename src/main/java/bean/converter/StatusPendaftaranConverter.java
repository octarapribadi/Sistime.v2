package bean.converter;

import model.StatusPendaftaran;

import javax.enterprise.context.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Named;

@Named
@FacesConverter("statusPendaftaranConverter")
public class StatusPendaftaranConverter implements Converter<Byte> {
    StatusPendaftaran statusPendaftaran=new StatusPendaftaran();
    @Override
    public Byte getAsObject(FacesContext context, UIComponent component, String value) {
        if(value.equals("MARKETING"))
            return 1;
        else if(value.equals("KEUANGAN"))
            return 2;
        else if(value.equals("PRODI"))
            return 3;
        else if(value.equals("DONE"))
            return 4;
        else
            return 0;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Byte value) {
        switch(value){
            case 1:
                return "MARKETING";
            case 2:
                return "KEUANGAN";
            case 3:
                return "PRODI";
            case 4:
                return "DONE";
            default:
                return null;
        }
    }
}
