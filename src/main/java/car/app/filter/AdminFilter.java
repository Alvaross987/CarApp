package car.app.filter;

import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;

public class AdminFilter {
	@AroundInvoke
	public Object auth(InvocationContext ctx) throws Exception {
		HttpHeaders headers = (HttpHeaders) ctx.getParameters()[0];
		String token = headers.getHeaderString("authorization");
		try {
			Algorithm algorithm = Algorithm.HMAC256("secret");
			JWTVerifier verifier =
					JWT.require(algorithm)
					   .withClaim("isadmin", 1)
					   .build();
			verifier.verify(token);
		} catch (JWTVerificationException exception) {
			return Response.status(Status.UNAUTHORIZED).entity("You need to be admin").build();
		}
		return ctx.proceed();
	}
}
