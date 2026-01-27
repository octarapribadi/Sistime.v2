package service;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.jboss.logging.Logger;
import bean.KhsMahasiswaBean;
import dto.KhsDto;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;


@Path("/khs")
@Produces(MediaType.APPLICATION_JSON)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class KhsMahasiswaService {

    @Inject
    KhsMahasiswaBean khsMahasiswaBean;

    @GET
    @Path("{iduser}")
    @RolesAllowed("mahasiswa")
    public Response getKhs(@PathParam("iduser") long idUser){
        try{
            List<KhsDto> khsDtoList = khsMahasiswaBean.getKhs(idUser);
            return Response.ok(khsDtoList,MediaType.APPLICATION_JSON).build();
        }
        catch(Exception ex){
            Logger.getLogger(KhsMahasiswaService.class).error(ex.getMessage());
            return Response.serverError().build();
        }
    }
}
