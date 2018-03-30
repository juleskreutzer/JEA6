package util.JWT;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.impl.crypto.MacProvider;

import javax.annotation.Priority;
import javax.crypto.KeyGenerator;
import javax.inject.Inject;
import javax.ws.rs.Priorities;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;
import java.io.IOException;
import java.security.Key;

@Provider
@JWTRequired
@Priority(Priorities.AUTHENTICATION)
public class JWTAuthFilter implements ContainerRequestFilter {



    @Override
    public void filter(ContainerRequestContext containerRequestContext) throws IOException {
        String authorizationHeader = containerRequestContext.getHeaderString("Authorization");
        String token = authorizationHeader.substring("Bearer".length()).trim();

        try {
            // Try to validate the token
            Key key = MacProvider.generateKey();
            Jwts.parser().setSigningKey(key).parseClaimsJws(token);
        } catch(SignatureException e) {
            // Token not valid
            containerRequestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).build());
        }


    }
}
