package com.example.demogrpc.server.Configs;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.convert.DurationUnit;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.temporal.ChronoUnit;

@Component
@Data
@ConfigurationProperties("grpc.server")
@SuppressWarnings("javadoc")
public class GrpcServerProperties {
    private int port = 9090;

    /**
     * The time to wait for the server to gracefully shutdown (completing all requests after the server started to
     * shutdown). If set to a negative value, the server waits forever. If set to {@code 0} the server will force
     * shutdown immediately. Defaults to {@code 30s}.
     *
     * @param gracefullShutdownTimeout The time to wait for a graceful shutdown.
     * @return The time to wait for a graceful shutdown.
     */
    @DurationUnit(ChronoUnit.SECONDS)
    private Duration shutdownGracePeriod = Duration.of(30, ChronoUnit.SECONDS);

    private boolean enableKeepAlive = true;

    @DurationUnit(ChronoUnit.HOURS)
    private Duration keepAliveTime = Duration.of(2, ChronoUnit.HOURS);

    @DurationUnit(ChronoUnit.SECONDS)
    private Duration keepAliveTimeout = Duration.of(20, ChronoUnit.SECONDS);

    @DurationUnit(ChronoUnit.MINUTES)
    private Duration permitKeepAliveTime = Duration.of(5, ChronoUnit.MINUTES);

    private boolean permitKeepAliveWithoutCalls = false;

    @DurationUnit(ChronoUnit.SECONDS)
    private Duration maxConnectionIdle = Duration.of(5, ChronoUnit.MINUTES);;

}
