package service;

import bean.StatusBean;
import dto.StatusDto;
import org.jboss.logging.Logger;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/status")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class StatusService {
    @Inject
    StatusBean statusBean;

    @GET
    @RolesAllowed({"administrator","mahasiswa"})
    public Response getStatus() {
        try {
            List<StatusDto> dto = statusBean.getStatus();
            return Response.ok(dto).build();
        } catch (
                Exception ex) {
            Logger.getLogger(this.getClass()).error(ex);
            return Response.serverError().build();
        }
    }
}
