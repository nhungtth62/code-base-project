package com.example.demogrpc.client.service;


import com.example.demogrpc.client.ChannelManager;
import com.example.demogrpc.grpc.Hello.HelloRequest;
import com.example.demogrpc.grpc.Hello.HelloResponse;
import com.example.demogrpc.grpc.Hello.HelloServiceGrpc;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HelloService {
    @Autowired
    private ChannelManager channelManager;
    private HelloServiceGrpc.HelloServiceBlockingStub stub;

    @PostConstruct
    void init() {
       stub = HelloServiceGrpc
               .newBlockingStub(channelManager.getChannel1());
   }

    public HelloResponse getHelloResponse() {
        HelloResponse helloResponse = stub.hello(HelloRequest.newBuilder()
                .setFirstName("Nhung")
                .setLastName("gRPC")
                .build());
        return helloResponse;
    }

    @PreDestroy
    public void teardown() {
        channelManager.getChannel1().shutdown();
    }
}
