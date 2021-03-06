package car.app.exception;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.ejb.Singleton;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.apache.log4j.Logger;

import car.app.entity.ErrorMessage;

@Singleton
@Provider
public class ConstraintViolationMapper implements ExceptionMapper<ConstraintViolationException> {

    @Override
    public Response toResponse(ConstraintViolationException e) {
    	final Logger logger = Logger.getLogger(ConstraintViolationMapper.class);
		
        List<ErrorMessage> messages = new ArrayList<ErrorMessage>();
        e.getConstraintViolations().stream()
            .map(ConstraintViolation::getMessage)
            .collect(Collectors.toList()).forEach(error -> messages.add(new ErrorMessage(error, 400, "")));
    logger.error(messages);
		return Response.status(Status.BAD_REQUEST).type(MediaType.APPLICATION_JSON).entity(messages).build();
    }

}
