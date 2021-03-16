package br.com.lucashilles.keystone.utils;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 * @author Eduardo Folly
 */
@Provider
public class WebApplicationExceptionHandler
        implements ExceptionMapper<WebApplicationException> {

    @Override
    public Response toResponse(WebApplicationException exception) {

        int status = exception.getResponse().getStatus();
        String message = exception.getMessage();

        switch (status) {
            case 401:
                message = "Aceso não autorizado.";
                break;
            case 403:
                message = "Acesso não permitido.";
                break;
            case 404:
                message = "Não encontrado.";
                break;
            default:
                if (message == null || message.trim().isEmpty())
                    message = "Bad Request - " + status;
        }

        return Response.status(status)
                       .entity(new ApplicationError(message, status))
                       .build();
    }

}
