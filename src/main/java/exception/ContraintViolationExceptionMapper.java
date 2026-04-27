package exception;

import dto.ErrorResponse;

import javax.validation.ConstraintViolationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import java.util.List;
import java.util.stream.Collectors;

@Provider
public class ContraintViolationExceptionMapper implements ExceptionMapper<ConstraintViolationException> {
    @Override
    public Response toResponse(ConstraintViolationException e) {
        List<ErrorResponse> errors = e.getConstraintViolations()
                .stream()
                .map(v -> new ErrorResponse(v.getMessage(), 400))
                .collect(Collectors.toList());
        return Response.status(Response.Status.BAD_REQUEST)
                .entity(errors)
                .build();
    }
}
