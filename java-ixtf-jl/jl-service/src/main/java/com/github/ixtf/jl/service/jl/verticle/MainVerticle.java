package com.github.ixtf.jl.service.jl.verticle;

import com.github.ixtf.api.vertx.ServiceServerVerticle;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.DeploymentOptions;

import static io.vertx.core.VertxOptions.DEFAULT_EVENT_LOOP_POOL_SIZE;

public class MainVerticle extends AbstractVerticle {

    @Override
    public void start() throws Exception {
        var deploymentOptions = new DeploymentOptions().setWorker(true).setInstances(DEFAULT_EVENT_LOOP_POOL_SIZE);
        vertx.deployVerticle(ServiceServerVerticle.class, deploymentOptions);
    }
}
