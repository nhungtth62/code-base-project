package com.example.demogrpc.server.Impl;

import com.example.demogrpc.grpc.Hello.HelloRequest;
import com.example.demogrpc.grpc.Hello.HelloResponse;
import com.example.demogrpc.grpc.Hello.HelloServiceGrpc;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@GrpcService
@Service
public class HelloServiceImpl extends HelloServiceGrpc.HelloServiceImplBase {
    Logger logger = LoggerFactory.getLogger(HelloServiceImpl.class);

    @Override
    public void hello(HelloRequest request, StreamObserver<HelloResponse> responseObserver) {
        logger.info("Get request = {} {}", request.getFirstName(), request.getLastName());

        String greeting = "Hello, " +
                request.getFirstName() +
                " " +
                request.getLastName();

        HelloResponse response = HelloResponse.newBuilder()
                .setGreeting(greeting)
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
