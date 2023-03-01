package com.example.demogrpc.server.Security.interceptor;

import com.example.demogrpc.server.Constants;
import io.grpc.Metadata;
import io.grpc.ServerCall;
import io.grpc.ServerCallHandler;
import io.grpc.ServerInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class Server1RequestInterceptor implements ServerInterceptor {
    @Override
    public <ReqT, RespT> ServerCall.Listener<ReqT> interceptCall(
            ServerCall<ReqT, RespT> serverCall,
            Metadata metadata,
            ServerCallHandler<ReqT, RespT> serverCallHandler) {

        log.info("Validating user token");
        var userToken = metadata.get(Metadata.Key.of(Constants.Authorization, Metadata.ASCII_STRING_MARSHALLER));
        validateUserToken(userToken);
        return serverCallHandler.startCall(serverCall, metadata);
    }

    private void validateUserToken(String userToken) {
        log.info("Received token: {}", userToken);
    }
}
