package service;

import bean.WaktuKuliahBean;
import org.jboss.logging.Logger;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.persistence.NoResultException;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/waktukuliah")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class WaktuKuliahService {
    @Inject
    WaktuKuliahBean waktuKuliahBean;

    @GET
    @RolesAllowed({"administrator", "mahasiswa"})
    public Response getWaktuKuliah(){
        try {
            return Response.ok(waktuKuliahBean.getWaktuKuliah())
                    .build();
        } catch (NoResultException ex) {
            return Response.status(Response.Status.NOT_FOUND).build();
        } catch (ForbiddenException ex) {
            return Response.status(Response.Status.FORBIDDEN).build();
        } catch (Exception ex) {
            Logger.getLogger(this.getClass()).error(ex);
            return Response.serverError().build();
        }
    }
}
