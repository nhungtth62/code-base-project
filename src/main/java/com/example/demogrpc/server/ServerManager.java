package com.example.demogrpc.server;

import com.example.demogrpc.server.Impl.CityScoreServiceImpl;
import com.example.demogrpc.server.Impl.HelloServiceImpl;
import com.example.demogrpc.server.Security.interceptor.Server1RequestInterceptor;
import com.example.demogrpc.server.Security.interceptor.Server1ResponseInterceptor;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.ServerInterceptor;
import io.grpc.ServerInterceptors;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Getter
@AllArgsConstructor
public class ServerManager {
    private final Server server1;

    private final Server server2;

    private List<ServerInterceptor> serverInterceptorList = new ArrayList<>();

    ServerManager() {
        serverInterceptorList.add(new Server1RequestInterceptor());
        serverInterceptorList.add(new Server1ResponseInterceptor());

        server1 = ServerBuilder
                .forPort(8081)
                .addService(ServerInterceptors.intercept(new HelloServiceImpl(), serverInterceptorList)).build();

        server2 = ServerBuilder
                .forPort(8082)
                .addService(new CityScoreServiceImpl()).build();

    }
}
