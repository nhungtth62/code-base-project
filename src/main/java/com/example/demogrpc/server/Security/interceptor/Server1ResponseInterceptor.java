package com.example.demogrpc.server.Security.interceptor;

import io.grpc.*;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Server1ResponseInterceptor implements ServerInterceptor {
    @Override
    public <ReqT, RespT> ServerCall.Listener<ReqT> interceptCall(
            ServerCall<ReqT, RespT> serverCall,
            Metadata metadata,
            ServerCallHandler<ReqT, RespT> serverCallHandler) {
        return serverCallHandler.startCall(
                new ForwardingServerCall.SimpleForwardingServerCall<>(serverCall) {
                    @Override
                    public void sendMessage(RespT message) {
                        log.info("Message being sent to client : " + message);
                        super.sendMessage(message);
                    }
                },
                metadata);
    }
}
