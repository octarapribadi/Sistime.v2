package service;

import bean.AuthBean;
import bean.TokenFactoryBean;
import dto.Login;
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
import java.util.Map;

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
    public Response login(Login login) {
        try {
            String username = login.getUsername();
            String password = login.getPassword();
            if (username == null || password == null)
                return Response.status(Response.Status.BAD_REQUEST)
                        .entity("Username atau password tidak boleh kosong")
                        .build();
            System.out.println("isi password:" + password);
            boolean status = authBean.auth(username, password);
            if (!status)
                return Response.status(401)
                        .entity("Kredensial salah")
                        .build();

            String token = tokenFactoryBean.generate(username, "administrator", 120);
            Map<String, String> response = new HashMap<>();
            response.put("token", token);

            return Response.status(200).entity(response).build();

        } catch (Exception e) {
            Logger.getLogger(LoginService.class).error("error", e);
            return Response.status(500)
                    .entity("Terjadi kesalahan pada server")
                    .build();
        }


//        try {
//            identity = domain.authenticate(login.getUsername(), new PasswordGuessEvidence(login.getPassword().toCharArray()));
//        } catch (RealmUnavailableException ex1) {
//            return Response.status(Response.Status.SERVICE_UNAVAILABLE)
//                    .entity(new ErrorResponse("AuthService tidak tersedia"))
//                    .build();
//        } catch (SecurityException ex2) {
//            return Response.status(Response.Status.UNAUTHORIZED)
//                    .entity(new ErrorResponse("Kredensial salah"))
//                    .build();
//        }
//
//        Set<String> roles = new HashSet<>();
//        for (String role : identity.getRoles()) {
//            roles.add(role);
//        }
//
//        Instant now = Instant.now();
//        String token = Jwt.issuer("octara-issuer")
//                .upn(identity.getPrincipal().getName())
//                .subject(identity.getPrincipal().getName())
//                .groups(roles)
//                .audience("rest-api")
//                .issuedAt(now)
//                .expiresAt(now.plus(2, ChronoUnit.HOURS))
//                .sign();
//        return Response.ok(new Token(token)).build();
    }
}
