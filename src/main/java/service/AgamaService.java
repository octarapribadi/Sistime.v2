package service;

import bean.AgamaBean;
import org.jboss.logging.Logger;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.persistence.NoResultException;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/agama")
@Produces(MediaType.APPLICATION_JSON)
public class AgamaService {
    @Inject
    AgamaBean agamaBean;

    @GET
    @RolesAllowed({"mahasiswa","administrator"})
    public Response getAgamas() {
        try {
            return Response.ok(agamaBean.getAgama()).build();
        }
        catch(NoResultException ex){
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        catch(ForbiddenException ex){
            return Response.status(Response.Status.FORBIDDEN).build();
        }
        catch(Exception ex){
            Logger.getLogger(this.getClass()).error(ex);
            return Response.serverError().build();
        }
    }
}
