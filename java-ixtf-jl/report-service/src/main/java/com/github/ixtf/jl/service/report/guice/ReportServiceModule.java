package com.github.ixtf.jl.service.report.guice;

import com.github.ixtf.api.guice.ApiModule;
import com.github.ixtf.jl.service.report.application.DashboardService;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;

import java.util.Collection;
import java.util.Set;

public class ReportServiceModule extends ApiModule {
    public static final String SERVICE = "xyz.ixtf.jl.proto.ReportService";

    public ReportServiceModule(Vertx vertx, JsonObject config) {
        super(vertx, SERVICE, config);
    }

    @Override
    protected Collection<String> ActionPackages() {
        return Set.of(DashboardService.class.getPackageName());
    }
}
