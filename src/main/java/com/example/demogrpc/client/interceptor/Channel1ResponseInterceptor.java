package com.example.demogrpc.client.interceptor;

import io.grpc.*;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Channel1ResponseInterceptor implements ClientInterceptor {
    @Override
    public <ReqT, RespT> ClientCall<ReqT, RespT> interceptCall(
            MethodDescriptor<ReqT, RespT> methodDescriptor,
            CallOptions callOptions,
            Channel channel) {

        return new ForwardingClientCall.SimpleForwardingClientCall<>(
                channel.newCall(methodDescriptor, callOptions)) {

            @Override
            public void start(Listener<RespT> responseListener, Metadata headers) {
                super.start(
                        new ForwardingClientCallListener.SimpleForwardingClientCallListener<>(
                                responseListener) {
                            @Override
                            public void onMessage(RespT message) {
                                log.info("Received response from Server: {}", message.toString());
                                super.onMessage(message);
                            }
                        },
                        headers);
            }
        };
    }
}
