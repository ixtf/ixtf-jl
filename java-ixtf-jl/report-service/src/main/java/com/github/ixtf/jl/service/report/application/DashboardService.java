package com.github.ixtf.jl.service.report.application;

import com.github.ixtf.api.ApiAction;
import com.github.ixtf.api.ApiContext;
import com.github.ixtf.jl.service.report.application.internal.DashboardServiceImpl;
import com.google.inject.ImplementedBy;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Context;
import reactor.core.publisher.Mono;

import java.util.Map;

import static com.github.ixtf.jl.service.report.guice.ReportServiceModule.SERVICE;
import static jakarta.ws.rs.core.MediaType.APPLICATION_JSON;

@Path("/services/" + SERVICE)
@Consumes(APPLICATION_JSON)
@Produces(APPLICATION_JSON)
@ImplementedBy(DashboardServiceImpl.class)
public interface DashboardService {
    @Path("/actions/Dashboard")
    @GET()
    @ApiAction(service = SERVICE, action = "Dashboard")
    Mono<Map> get(@Context ApiContext ac);
}
