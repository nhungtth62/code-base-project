package com.example.demogrpc.server.ExceptionHandler;

import net.devh.boot.grpc.server.advice.GrpcAdvice;

@GrpcAdvice
public class CityScoreExceptionHandler {

//    public StatusRuntimeException handleValidationError(CityScoreException cause) {
//
//        Instant time = Instant.now();
//        Timestamp timestamp = Timestamp.newBuilder().setSeconds(time.getEpochSecond())
//                .setNanos(time.getNano()).build();
//
//        CityScoreExceptionResponse exceptionResponse =
//                CityScoreExceptionResponse.newBuilder()
//                        .setErrorCode(cause.getErrorCode())
//                        .setTimestamp(timestamp)
//                        .build();
//
//
//        Status status = Status.newBuilder()
//                .setCode(Code.INVALID_ARGUMENT.getNumber())
//                .setMessage("Invalid city code")
//                .addDetails(Any.pack(exceptionResponse))
//                .build();
//
//        return StatusProto.toStatusRuntimeException(status);
//    }

}
