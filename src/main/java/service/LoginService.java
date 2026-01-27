package service;

import bean.AuthBean;
import bean.TokenFactoryBean;
import dto.LoginDto;
import entity.UserRole;
import org.jboss.logging.Logger;
import repo.UserRoleManager;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Path("/login")
public class LoginService {
    @Inject
    AuthBean authBean;

    @Inject
    TokenFactoryBean tokenFactoryBean;

    @Inject
    UserRoleManager userRoleManager;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response login(LoginDto loginDto) {
        try {
            String username = loginDto.getUsername();
            String password = loginDto.getPassword();
            if (username == null || password == null)
                return Response.status(Response.Status.BAD_REQUEST)
                        .entity("Username atau password tidak boleh kosong")
                        .build();
            boolean status = authBean.auth(username, password);
            if (!status)
                return Response.status(401)
                        .entity("Kredensial salah")
                        .build();
            List<UserRole> userRolesList = userRoleManager.findUserRoleByUsername(username);
            Set<String> userRolesSet = userRolesList.stream().map(userRole -> userRole.getRole().getRole()).collect(Collectors.toSet());
            String token = tokenFactoryBean.generate(username, userRolesSet);
            Map<String, String> response = new HashMap<>();
            response.put("token", token);

            return Response.status(200).entity(response).build();

        } catch (Exception e) {
            Logger.getLogger(LoginService.class).error("error", e);
            return Response.status(500)
                    .entity("Terjadi kesalahan pada server")
                    .build();
        }
    }
}
