package com.example.demogrpc.server.Impl;

import com.example.demogrpc.grpc.Base.BaseResponse;
import com.example.demogrpc.grpc.Base.StatusCodeOuterClass;
import com.example.demogrpc.grpc.CityCore.CityCoreResponse;
import com.example.demogrpc.grpc.CityCore.CityScoreRequestOuterClass;
import com.example.demogrpc.grpc.CityCore.CityScoreServiceGrpc;
import com.example.demogrpc.server.Constants;
import com.google.protobuf.Any;
import io.grpc.Context;
import io.grpc.Status;

import io.grpc.stub.StreamObserver;
import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.server.service.GrpcService;

@Slf4j
@GrpcService
public class CityScoreServiceImpl extends CityScoreServiceGrpc.CityScoreServiceImplBase {
    @Override
    public void calculateCityScore(CityScoreRequestOuterClass.CityScoreRequest request,
                                   StreamObserver<BaseResponse.Response> responseObserver) {

        Context.Key<String> key = Constants.CONTEXT_USERNAME_KEY;
        String value = key.get(Context.current());
        log.info("Request received from user: {}", value);

        if (request.getCityCode() == 0) {
            Status status = Status.INVALID_ARGUMENT.withDescription("City code is invalid");
            responseObserver.onError(status.asRuntimeException());
            return;
        }
        CityCoreResponse.CityScoreResponse scoreResponse = CityCoreResponse.CityScoreResponse
                                                                            .newBuilder()
                                                                            .setScore(request.getCityCode() * 10)
                                                                            .build();
        BaseResponse.Response response = BaseResponse.Response
                                                    .newBuilder()
                                                    .setMessage("ok")
                                                    .setData(Any.pack(scoreResponse))
                                                    .setStatus(StatusCodeOuterClass.StatusCode.SUCCESS)
                                                    .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
