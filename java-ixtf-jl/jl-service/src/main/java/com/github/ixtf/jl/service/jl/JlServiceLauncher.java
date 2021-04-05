package com.github.ixtf.jl.service.jl;

import com.github.ixtf.api.guice.MongoModule;
import com.github.ixtf.api.guice.RabbitMQModule;
import com.github.ixtf.jl.service.jl.guice.JlServiceModule;
import io.vertx.core.DeploymentOptions;
import io.vertx.core.Launcher;
import io.vertx.core.Vertx;
import io.vertx.core.VertxOptions;
import io.vertx.micrometer.MicrometerMetricsOptions;
import io.vertx.micrometer.VertxPrometheusOptions;
import org.slf4j.LoggerFactory;

import java.time.Duration;

import static com.github.ixtf.api.Util.config;
import static com.github.ixtf.guice.GuiceModule.init;
import static java.util.Optional.ofNullable;

public class JlServiceLauncher extends Launcher {
    public static void main(String[] args) {
        new JlServiceLauncher().dispatch(args);
    }

    @Override
    public void beforeStartingVertx(VertxOptions options) {
        final var prometheusOptions = new VertxPrometheusOptions().setEnabled(true).setPublishQuantiles(true);
        final var metricsOptions = new MicrometerMetricsOptions().setEnabled(true).setPrometheusOptions(prometheusOptions);
        options.setMaxWorkerExecuteTime(Duration.ofHours(1).toNanos()).setMetricsOptions(metricsOptions);
        ofNullable(System.getProperty("hazelcast.local.publicAddress")).ifPresent(it -> options.getEventBusOptions().setHost(it).setClusterPublicHost(it));
    }

    @Override
    public void afterStartingVertx(Vertx vertx) {
        super.afterStartingVertx(vertx);
        final var config = config("JL_ROOT_PATH", "/home/data/jl/config.yml");
        init(new JlServiceModule(vertx, config), new MongoModule(), new RabbitMQModule());
    }

    @Override
    public void handleDeployFailed(Vertx vertx, String mainVerticle, DeploymentOptions deploymentOptions, Throwable cause) {
        LoggerFactory.getLogger(getClass()).error("handleDeployFailed {}", mainVerticle, cause);
        super.handleDeployFailed(vertx, mainVerticle, deploymentOptions, cause);
    }

    @Override
    public void beforeStoppingVertx(Vertx vertx) {
        super.beforeStoppingVertx(vertx);
        System.out.println("beforeStoppingVertx");
    }
}
