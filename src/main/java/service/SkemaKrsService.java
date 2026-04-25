package service;

import bean.SkemaKrsBean;
import dto.ErrorResponse;
import dto.SkemaKrsDto;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/skemakrs")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class SkemaKrsService {
    @Inject
    SkemaKrsBean skemaKrsBean;

    @GET
    @RolesAllowed({"administrator", "mahasiswa"})
    public Response getSkemaKrs() {
        List<SkemaKrsDto> skemaKrsDtos = skemaKrsBean.findAll();
        if (skemaKrsDtos == null)
            return Response
                    .status(Response.Status.NO_CONTENT)
                    .entity(new ErrorResponse("data tidak ditemukan", Response.Status.NO_CONTENT.getStatusCode()))
                    .build();
        return Response.ok(skemaKrsDtos).build();
    }

    @POST
    @RolesAllowed({"administrator"})
    public Response setSkemaKrs(@Valid SkemaKrsDto dto) {
        try {
            skemaKrsBean.persist(dto);
            return Response.status(Response.Status.CREATED)
                    .entity(dto)
                    .build();
        } catch (WebApplicationException ex) {
            return Response
                    .status(ex.getResponse().getStatus())
                    .entity(new ErrorResponse(ex.getMessage(),ex.getResponse().getStatus()))
                    .build();
        }
    }

    @DELETE
    @RolesAllowed({"administrator"})
    @Path("/{id}")
    public Response removeSkemaKrs(@PathParam("id") Long id){
        try {
            skemaKrsBean.remove(id);
            return Response.ok().build();
        }
        catch(WebApplicationException ex){
            return Response
                    .status(ex.getResponse().getStatus())
                    .entity(new ErrorResponse(ex.getMessage(),ex.getResponse().getStatus()))
                    .build();
        }
    }

}
