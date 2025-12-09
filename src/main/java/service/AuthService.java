package service;

import dto.ErrorResponse;
import dto.Login;
import dto.Token;
import io.smallrye.jwt.build.Jwt;
import org.wildfly.security.auth.server.RealmUnavailableException;
import org.wildfly.security.auth.server.SecurityDomain;
import org.wildfly.security.auth.server.SecurityIdentity;
import org.wildfly.security.evidence.PasswordGuessEvidence;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.HashSet;
import java.util.Set;

@Path("/login")
public class AuthService {
    SecurityDomain domain = SecurityDomain.getCurrent();
    SecurityIdentity identity;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response login(Login login){
        try{
            identity = domain.authenticate(login.getUsername(), new PasswordGuessEvidence(login.getPassword().toCharArray()));
        }
        catch(RealmUnavailableException ex1){
            return Response.status(Response.Status.SERVICE_UNAVAILABLE)
                    .entity(new ErrorResponse("AuthService tidak tersedia"))
                    .build();
        }
        catch(SecurityException ex2){
            return Response.status(Response.Status.UNAUTHORIZED)
                    .entity(new ErrorResponse("Kredensial salah!"))
                    .build();
        }

        Set<String> roles = new HashSet<>();
        for (String role : identity.getRoles()) {
            roles.add(role);
        }

        Instant now = Instant.now();
        String token = Jwt.issuer("octara-issuer")
                .upn(identity.getPrincipal().getName())
                .subject(identity.getPrincipal().getName())
                .groups(roles)
                .audience("rest-api")
                .issuedAt(now)
                .expiresAt(now.plus(2, ChronoUnit.HOURS))
                .sign();
        return Response.ok(new Token(token)).build();
    }
}
