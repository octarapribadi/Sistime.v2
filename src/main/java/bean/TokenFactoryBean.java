package bean;

import io.smallrye.jwt.build.Jwt;

import javax.enterprise.context.ApplicationScoped;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Set;

@ApplicationScoped
public class TokenFactoryBean {
    public String generate(String username, Set<String> roles, long expiredInMin){
        return Jwt.issuer("https://portal.stmik-time.ac.id/rest/api/login/")
                .upn(username)
                .subject(username)
                .groups(roles)
                .audience("sistimev2")
                .issuedAt(Instant.now())
                .expiresAt(Instant.now().plus(expiredInMin, ChronoUnit.MINUTES))
                .sign();
    }
}
