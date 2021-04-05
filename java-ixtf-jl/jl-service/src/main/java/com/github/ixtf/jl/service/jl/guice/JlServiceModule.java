package com.github.ixtf.jl.service.jl.guice;

import com.github.ixtf.api.guice.ApiModule;
import com.github.ixtf.jl.domain.listener.EntityListener;
import com.github.ixtf.jl.service.jl.application.AuthService;
import com.github.ixtf.jl.service.jl.application.internal.EntityListenersServiceImpl;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;

import java.util.Collection;
import java.util.Set;

public class JlServiceModule extends ApiModule {
    public static final String SERVICE = "com.github.ixtf.jl.proto.JlService";

    public JlServiceModule(Vertx vertx, JsonObject config) {
        super(vertx, SERVICE, config);
    }

    @Override
    protected void configure() {
        super.configure();
        bind(EntityListener.Service.class).to(EntityListenersServiceImpl.class);
    }

    @Override
    protected Collection<String> ActionPackages() {
        return Set.of(AuthService.class.getPackageName());
    }

}
