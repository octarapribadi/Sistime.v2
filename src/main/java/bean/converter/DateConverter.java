package bean.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Named;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@Named
@FacesConverter("dateConverter")
public class DateConverter implements Converter<LocalDate> {
    @Override
    public LocalDate getAsObject(FacesContext context, UIComponent component, String value) {

        if(value!=null && !value.isEmpty()){

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            try {
                Date myDate = sdf.parse(value);
                return myDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, LocalDate value) {
        return value.format(DateTimeFormatter.ofPattern("yyyy-LL-dd"));
    }
}
