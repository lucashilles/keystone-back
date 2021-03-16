package br.com.lucashilles.keystone.services;

import br.com.lucashilles.keystone.models.User;
import org.jboss.resteasy.annotations.jaxrs.PathParam;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("/api/user")
public class UserService {


    @GET
    @RolesAllowed("user")
    @Path("/{id}")
    User getById(@PathParam long id) {
        return User.findById(id);
    }

}
