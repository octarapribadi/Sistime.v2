package exception;

import dto.ErrorResponse;

import javax.transaction.RollbackException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class RollbackExceptionMapper implements ExceptionMapper<RollbackException> {
    @Override
    public Response toResponse(RollbackException exception) {
        return Response
                .status(Response.Status.BAD_REQUEST)
                .entity(new ErrorResponse(exception.getCause().getMessage(),Response.Status.BAD_REQUEST.getStatusCode()))
                .build();
    }
}
