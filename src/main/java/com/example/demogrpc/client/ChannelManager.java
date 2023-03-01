package com.example.demogrpc.client;

import com.example.demogrpc.client.interceptor.Channel1RequestInterceptor;
import com.example.demogrpc.client.interceptor.Channel1ResponseInterceptor;
import io.grpc.ClientInterceptor;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import lombok.Getter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Getter
public class ChannelManager {
    private  ManagedChannel channel1;

    private ManagedChannel channel2;

    private List<ClientInterceptor> clientInterceptors = new ArrayList<>();


    ChannelManager() {
        clientInterceptors.add(new Channel1RequestInterceptor());
        clientInterceptors.add(new Channel1ResponseInterceptor());

        channel1 = ManagedChannelBuilder
                .forAddress("localhost", 8081)
                .intercept(clientInterceptors)
                .usePlaintext()
                .build();

        channel2 = ManagedChannelBuilder
                .forAddress("localhost", 8082)
                .usePlaintext()
                .build();
    }

}
