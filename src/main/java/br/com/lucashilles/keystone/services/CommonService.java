package br.com.lucashilles.keystone.services;

import br.com.lucashilles.keystone.models.User;
import io.quarkus.elytron.security.common.BcryptUtil;

import javax.annotation.security.RolesAllowed;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.SecurityContext;

@Path("/api")
public class CommonService {

    @GET
    @Path("/ping")
    @Produces(MediaType.TEXT_PLAIN)
    public String ping() {
        return "pong";
    }

    @GET
    @RolesAllowed("ENGINEER")
    @Path("/auth")
    @Produces(MediaType.APPLICATION_JSON)
    public User auth(@Context SecurityContext securityContext) {
        String email = securityContext.getUserPrincipal().getName();
        return User.find("email", email).firstResult();
    }

    @POST
    @Path("/register")
    @Transactional
    @Consumes(MediaType.APPLICATION_JSON)
    public void register(User user) {
        System.out.println(user.password);
        user.password = BcryptUtil.bcryptHash(user.password);
        user.persist();
    }
}
