package service;

import bean.PasswordBean;
import dto.PasswordDto;
import org.eclipse.microprofile.jwt.JsonWebToken;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("password")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class PasswordService {

    @Inject
    JsonWebToken jwt;

    @Inject
    PasswordBean passwordBean;

    @RolesAllowed({"administrator", "mahasiswa"})
    @POST
    public Response forgotPassword(PasswordDto dto) {
        passwordBean.forgotPassword(dto);
        return Response.ok().build();
    }
}
