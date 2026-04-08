package service;

import bean.SkedulBean;
import dto.ErrorResponse;
import dto.SkedulDto;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/skedul")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class SkedulService {

    @Inject
    SkedulBean skedulBean;

    @GET
    @RolesAllowed({"administrator","mahasiswa"})
    public Response getSkedul(@QueryParam("idSkemaKrs") Long idSkemaKrs) {
        if (idSkemaKrs == null) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("skemaKrsId wajib diisi")
                    .build();
        } else {
            List<SkedulDto> dtos = skedulBean.findSkedulByIdSkemaKrs(idSkemaKrs);
            return Response.ok().entity(dtos).build();
        }
    }

    @DELETE
    @RolesAllowed({"administrator"})
    @Path("/{id}")
    public Response removeSkedul(@PathParam("id") Long idSkedul) {
        try {
            skedulBean.remove(idSkedul);
            return Response.ok().build();
        } catch (WebApplicationException ex) {
            return Response.status(ex.getResponse().getStatus())
                    .entity(new ErrorResponse(ex.getMessage(), ex.getResponse().getStatus()))
                    .build();
        }

    }

    @POST
    @RolesAllowed({"administrator"})
    public Response addSkeduls(List<SkedulDto> dtos){
        skedulBean.persist(dtos);
        return Response.ok().entity(dtos).build();
    }

}
