package com.example.demogrpc.server.Security.interceptor;

import com.example.demogrpc.server.Constants;
import io.grpc.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class Server2RequestInterceptor implements ServerInterceptor {
    public ValidateToken validateToken;
    public Server2RequestInterceptor(){
        this.validateToken = new ValidateToken();
    }

    @Override
    public <ReqT, RespT> ServerCall.Listener<ReqT> interceptCall(ServerCall<ReqT, RespT> call,
                                                                 Metadata headers,
                                                                 ServerCallHandler<ReqT, RespT> next) {
        String token = headers.get(Metadata.Key.of(Constants.Authorization, Metadata.ASCII_STRING_MARSHALLER));

        var getValidToken = validateToken.getValidToken(token);
        if(getValidToken == null){
            call.close(Status.UNAUTHENTICATED.withDescription("No authentication header or Non bearer token provided or Rejected by Authentication Service"), headers);
            return new ServerCall.Listener<>() {};
        }else {
            Context context = Context
                    .current()
                    .withValue(Constants.CONTEXT_USERNAME_KEY, getValidToken.getBody().get("name", String.class));
            return Contexts.interceptCall(
                    context,
                    call,
                    headers,
                    next);
        }
    }

}
