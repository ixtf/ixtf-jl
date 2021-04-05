package com.github.ixtf.jl.service.jl.application;

import com.github.ixtf.api.ApiAction;
import com.github.ixtf.api.ApiContext;
import com.github.ixtf.jl.domain.Operator;
import com.github.ixtf.jl.service.jl.application.internal.AuthServiceImpl;
import com.google.inject.ImplementedBy;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Context;
import reactor.core.publisher.Mono;

import java.security.Principal;

import static com.github.ixtf.jl.service.jl.guice.JlServiceModule.SERVICE;
import static jakarta.ws.rs.core.MediaType.APPLICATION_JSON;

@Path("/services/" + SERVICE)
@Consumes(APPLICATION_JSON)
@Produces(APPLICATION_JSON)
@ImplementedBy(AuthServiceImpl.class)
public interface AuthService {

    Mono<Operator> find(Principal principal);

    @Path("/actions/AuthInfo")
    @GET()
    @ApiAction(service = SERVICE, action = "AuthInfo")
    default Mono<Operator> authInfo(@Context ApiContext ac) {
        return find(ac.principal());
    }
}
