package br.com.lucashilles.keystone.utils;

import io.quarkus.arc.ArcUndeclaredThrowableException;
import org.hibernate.exception.ConstraintViolationException;

import javax.persistence.PersistenceException;
import javax.transaction.RollbackException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 * @author Eduardo Folly
 */
@Provider
public class ArcUndeclaredThrowableExceptionHandler
        implements ExceptionMapper<ArcUndeclaredThrowableException> {

    @Override
    public Response toResponse(ArcUndeclaredThrowableException ex) {

        String message = "ArcUndeclaredThrowableException: " + ex.getMessage();

        if (ex.getCause() instanceof RollbackException) {
            RollbackException rbe = (RollbackException) ex.getCause();

            message = "RollbackException: " + rbe.getMessage();

            if (rbe.getCause() instanceof PersistenceException) {
                PersistenceException pse =
                        (PersistenceException) rbe.getCause();

                message = "PersistenceException: " + pse.getMessage();

                if (pse.getCause() instanceof ConstraintViolationException) {
                    ConstraintViolationException cve =
                            (ConstraintViolationException) pse.getCause();

                    message = ConstraintHelper.getMessage(
                            cve.getConstraintName());
                }

            }

        }

        return Response.status(500)
                       .entity(new ApplicationError(message, 500))
                       .build();
    }

}
