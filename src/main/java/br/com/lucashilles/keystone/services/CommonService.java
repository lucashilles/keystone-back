package br.com.lucashilles.keystone.services;

import br.com.lucashilles.keystone.models.User;
import io.quarkus.elytron.security.common.BcryptUtil;

import javax.annotation.security.RolesAllowed;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.SecurityContext;
import java.util.HashMap;
import java.util.Map;

@Path("/api")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CommonService {

    @GET
    @Path("/ping")
    public Map<String, String> ping() {
        Map<String, String> hashMap = new HashMap<>();
        hashMap.put("result", "pong");
        return hashMap;
    }

    @GET
    @RolesAllowed("ENGINEER")
    @Path("/auth")
    public User auth(@Context SecurityContext securityContext) {
        String email = securityContext.getUserPrincipal().getName();
        return User.find("email", email).firstResult();
    }

    @POST
    @Path("/register")
    @Transactional
    public void register(User user) {
        System.out.println(user.password);
        user.password = BcryptUtil.bcryptHash(user.password);
        user.persist();
    }
}
