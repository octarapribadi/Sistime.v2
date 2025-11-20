package service;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.jboss.logging.Logger;
import business.KhsMahasiswaCDI;
import dto.KhsDTO;

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
    KhsMahasiswaCDI khsMahasiswaCDI;

    @GET
    @Path("{iduser}")
    public Response getKhs(@PathParam("iduser") long idUser){
        try{
            List<KhsDTO> khsDTOList = khsMahasiswaCDI.getKhs(idUser);
            return Response.ok(khsDTOList,MediaType.APPLICATION_JSON).build();
        }
        catch(Exception ex){
            Logger.getLogger(KhsMahasiswaService.class).error(ex.getMessage());
            return Response.serverError().build();
        }
    }
}
