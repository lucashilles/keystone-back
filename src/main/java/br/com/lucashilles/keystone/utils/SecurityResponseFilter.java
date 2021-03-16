package br.com.lucashilles.keystone.utils;

import javax.annotation.Priority;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.ext.Provider;
import java.util.HashMap;
import java.util.Map;

@Provider
@Priority(1)
public class SecurityResponseFilter implements ContainerResponseFilter {

    @Override
    public void filter(ContainerRequestContext requestContext,
                       ContainerResponseContext responseContext) {

        responseContext.getHeaders()
                       .putSingle("Content-Type",
                                  "application/json; charset=utf-8");

        Object entity = responseContext.getEntity();
        int status = responseContext.getStatus();
        String message = "OK";

        if (entity instanceof ApplicationError) {
            ApplicationError applicationError = (ApplicationError) entity;
            message = applicationError.getMessage();
            status = applicationError.getStatus();
            responseContext.setStatus(status);
            entity = null;
        }

        Map<String, Object> map = new HashMap<>();

        map.put("entity", entity);
        map.put("status", status);
        map.put("message", message);

        responseContext.setEntity(map);

    }

}
