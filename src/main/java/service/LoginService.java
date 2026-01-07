package service;

import dto.Login;
import entity.User;
import org.jboss.logging.Logger;
import org.mindrot.jbcrypt.BCrypt;
import repo.UserManager;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/login")
public class LoginService {

//    SecurityDomain domain = SecurityDomain.getCurrent();
//    SecurityIdentity identity;
    @Inject
    UserManager userManager;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response login(Login login) {
        try{
            String username = login.getUsername();
            String password = login.getPassword();
            User user = userManager.findUserByUsername(username);
            Boolean status = BCrypt.checkpw(password,user.getPassword());
            if(status)
                return Response.status(200).entity("{\"token\":\"ok\"}").build();
            else
                return Response.status(401).entity("Kredensial salah").build();
        }
        catch(Exception e){
            Logger.getLogger(LoginService.class).error(e.getMessage());
            return Response.status(500).entity(e.getMessage()).build();
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
