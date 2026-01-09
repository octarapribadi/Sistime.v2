package bean;

import io.smallrye.jwt.build.Jwt;

import javax.enterprise.context.ApplicationScoped;
import java.time.Instant;
import java.time.temporal.ChronoUnit;

@ApplicationScoped
public class TokenFactoryBean {
    public String generate(String username, String role, long expiredInMin){
        return Jwt.issuer("octara-issuer")
                .upn(username)
                .subject(username)
                .groups(role)
                .audience("rest-api")
                .issuedAt(Instant.now())
                .expiresAt(Instant.now().plus(expiredInMin, ChronoUnit.MINUTES))
                .sign();
    }
}
