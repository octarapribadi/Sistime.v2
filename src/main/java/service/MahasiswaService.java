package service;

import bean.MahasiswaBean;
import entity.Mahasiswa;
import org.eclipse.microprofile.jwt.JsonWebToken;
import org.jboss.logging.Logger;

import javax.annotation.PostConstruct;
import javax.annotation.security.RolesAllowed;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.security.Principal;

@Path("/mahasiswa")
public class MahasiswaService {

    @Inject
    MahasiswaBean mahasiswaBean;

    @Inject
    JsonWebToken token;

    @GET
    @Path("/{nim}")
    @RolesAllowed({"mahasiswa"})
    @Produces(MediaType.APPLICATION_JSON)
    public Response getMahasiswaByNim(@PathParam("nim") String nim) {
        Mahasiswa dto = mahasiswaBean.getMahasiswaByNim(nim);
        if (!token.getClaim("sub").equals(dto.getUser().getId().toString()))
            return Response.status(Response.Status.FORBIDDEN).build();
        return Response.ok(dto).build();
    }
}
