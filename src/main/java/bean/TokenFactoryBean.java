package bean;

import io.smallrye.jwt.build.Jwt;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import repo.UserManager;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Set;

@ApplicationScoped
public class TokenFactoryBean {
    @Inject
    UserManager userManager;

    @Inject
    @ConfigProperty(name = "sistimev2.jwt.expiredtimeinminutes")
    String expired;

    public String generate(String username, Set<String> roles) {
        return Jwt.issuer("https://portal.stmik-time.ac.id/rest/api/login/")
                .upn(username)
                .subject(userManager.findUserByUsername(username).getId().toString())
                .groups(roles)
                .audience("sistimev2")
                .issuedAt(Instant.now())
                .expiresAt(Instant.now().plus(Long.parseLong(expired), ChronoUnit.MINUTES))
                .sign();
    }
}

