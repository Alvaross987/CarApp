package car.app.exception;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.apache.log4j.Logger;

import car.app.entity.ErrorMessage;

@Provider
public class DataNotFoundExceptionMapper implements ExceptionMapper<DataNotFoundException> {

	@Override
	public Response toResponse(DataNotFoundException ex) {
		final Logger logger = Logger.getLogger(DataNotFoundExceptionMapper.class);
		logger.error(ex);
		ErrorMessage errorMessage = new ErrorMessage(ex.getMessage(), 404, "");
		return Response.status(Status.NOT_FOUND).type(MediaType.APPLICATION_JSON).entity(errorMessage.toString()).build();
	}

}
