package bean.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter("booleanConverter")
public class BooleanConverter implements Converter<Byte> {


    @Override
    public Byte getAsObject(FacesContext facesContext, UIComponent uiComponent, String s) {
        if(s.equals("true"))
            return 1;
        else
            return 0;
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Byte aByte) {
        if(aByte==1)
            return "true";
        else
            return "false";
    }
}
