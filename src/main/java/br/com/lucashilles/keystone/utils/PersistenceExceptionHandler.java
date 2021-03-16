package br.com.lucashilles.keystone.utils;

import org.hibernate.exception.ConstraintViolationException;

import javax.persistence.PersistenceException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 * @author Eduardo Folly
 */
@Provider
public class PersistenceExceptionHandler
        implements ExceptionMapper<PersistenceException> {

    @Override
    public Response toResponse(PersistenceException exception) {

        String message = exception.getMessage();

        if (exception.getCause() instanceof ConstraintViolationException) {
            ConstraintViolationException cve =
                    (ConstraintViolationException) exception.getCause();

            message = ConstraintHelper.getMessage(cve.getConstraintName());
        }

        return Response.status(500)
                       .entity(new ApplicationError(message, 500))
                       .build();
    }

}
