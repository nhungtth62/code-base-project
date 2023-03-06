package com.example.demogrpc.server;

import io.grpc.Context;
import io.grpc.Metadata;

import java.util.UUID;

import static io.grpc.Metadata.ASCII_STRING_MARSHALLER;

public class Constants {
    public static final String JWT_SIGNING_KEY = "L8hHXsaQOUjk5rg7XPGv4eL36anlCrkMz8CJ0i/8E/0=";
    public static final String Authorization = "Authorization";

    public static final Context.Key<String> CONTEXT_USERNAME_KEY = Context.key("username");

    public static final Context.Key<String> UUID = Context.key("uuid");
    public static final Context.Key<String> CLIENT_ID_CONTEXT_KEY = Context.key("clientId");

    private Constants() {
        throw new AssertionError();
    }
}
