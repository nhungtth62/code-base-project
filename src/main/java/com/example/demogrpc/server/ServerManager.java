package com.example.demogrpc.server;

import com.example.demogrpc.server.Impl.CityScoreServiceImpl;
import com.example.demogrpc.server.Impl.HelloServiceImpl;
import com.example.demogrpc.server.Security.interceptor.Server1RequestInterceptor;
import com.example.demogrpc.server.Security.interceptor.Server1ResponseInterceptor;
import com.example.demogrpc.server.Security.interceptor.Server2RequestInterceptor;
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
    private List<ServerInterceptor> server1InterceptorList = new ArrayList<>();
    private List<ServerInterceptor> server2InterceptorList = new ArrayList<>();

    ServerManager() {
        server1InterceptorList.add(new Server1RequestInterceptor());
        server1InterceptorList.add(new Server1ResponseInterceptor());
        server2InterceptorList.add(new Server2RequestInterceptor());

        server1 = ServerBuilder
                .forPort(8081)
                .addService(ServerInterceptors.intercept(new HelloServiceImpl(), server1InterceptorList)).build();

        server2 = ServerBuilder
                .forPort(8082)
                .addService(ServerInterceptors.intercept(new CityScoreServiceImpl(),server2InterceptorList)).build();

    }
}
