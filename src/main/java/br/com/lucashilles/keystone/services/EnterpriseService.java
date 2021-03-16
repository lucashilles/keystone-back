package br.com.lucashilles.keystone.services;

import br.com.lucashilles.keystone.models.Enterprise;
import br.com.lucashilles.keystone.models.User;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import org.jboss.resteasy.annotations.jaxrs.PathParam;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.SecurityContext;
import java.util.List;

@Path("/api/enterprise")
public class EnterpriseService {

    @GET
    @RolesAllowed("ENGINEER")
    @Path("/{id}")
    public Enterprise getById(@PathParam long id) {
        return Enterprise.findById(id);
    }

    @GET
    @RolesAllowed("ENGINEER")
    @Path("/")
    public List<Enterprise> getByUser(@Context SecurityContext securityContext) {
        String email = securityContext.getUserPrincipal().getName();
        User user = User.find("email", email).firstResult();
        return user.enterprises;
//        return Enterprise.find("users.id", user.id).list();
    }
}
