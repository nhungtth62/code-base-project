package com.example.demogrpc.client.credential;

import com.example.demogrpc.server.Constants;
import io.grpc.CallCredentials;
import io.grpc.Metadata;
import io.grpc.Status;
import java.util.concurrent.Executor;

public class AuthenticationCallCredentials extends CallCredentials {
    private String token;

    public AuthenticationCallCredentials(String token) {
        this.token = token;
    }

    @Override
    public void applyRequestMetadata(RequestInfo requestInfo,
                                     Executor executor,
                                     MetadataApplier metadataApplier) {

        executor.execute(() -> {
            try {
                Metadata headers = new Metadata();
                headers.put(Metadata.Key.of(Constants.Authorization, Metadata.ASCII_STRING_MARSHALLER), "Bearer " + token);
                metadataApplier.apply(headers);
            } catch (Throwable e) {
                metadataApplier.fail(Status.UNAUTHENTICATED.withCause(e));
            }
        });
    }

    @Override
    public void thisUsesUnstableApi() {

    }



}
