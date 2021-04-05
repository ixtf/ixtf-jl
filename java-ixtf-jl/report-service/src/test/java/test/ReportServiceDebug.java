package test;

import com.github.ixtf.jl.service.report.ReportServiceLauncher;
import com.github.ixtf.jl.service.report.verticle.MainVerticle;
import io.vertx.core.Vertx;
import io.vertx.core.VertxOptions;
import lombok.Cleanup;
import lombok.SneakyThrows;
import reactor.tools.agent.ReactorDebugAgent;

import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.concurrent.TimeUnit;

public class ReportServiceDebug extends ReportServiceLauncher {

    public static void main(String[] args) {
        ReactorDebugAgent.init();

        System.setProperty("vertx.hazelcast.config", "/home/data/jl/cluster.xml");
        System.setProperty("hazelcast.local.publicAddress", "172.10.10.111");
        System.setProperty("hazelcast.local.publicAddress", localIp());
        new ReportServiceDebug().dispatch(new String[]{"-cluster"});

//        new AlgServiceDebug().dispatch(args);
    }

    @SneakyThrows({SocketException.class, UnknownHostException.class})
    private static String localIp() {
        @Cleanup final var socket = new DatagramSocket();
        socket.connect(InetAddress.getByName("8.8.8.8"), 10002);
        return socket.getLocalAddress().getHostAddress();
    }

    @Override
    protected String getMainVerticle() {
        return MainVerticle.class.getName();
    }

    @Override
    public void beforeStartingVertx(VertxOptions options) {
        super.beforeStartingVertx(options);
        options.setMaxEventLoopExecuteTime(1).setMaxEventLoopExecuteTimeUnit(TimeUnit.DAYS);
    }

    @Override
    public void afterStartingVertx(Vertx vertx) {
        super.afterStartingVertx(vertx);
    }

}
