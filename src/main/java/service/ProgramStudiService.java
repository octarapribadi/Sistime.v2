package service;

import bean.ProgramStudiBean;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/programstudi")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ProgramStudiService {
    @Inject
    ProgramStudiBean programStudiBean;


    @GET
    @RolesAllowed({"administrator", "mahasiswa"})
    public Response getProgramStudi() {
        return Response.ok(programStudiBean.getProgramStudi())
                .build();
    }
}
