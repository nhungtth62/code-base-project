package com.example.demogrpc.client.Service;

import com.example.demogrpc.client.ChannelManager;
import com.example.demogrpc.grpc.Base.BaseResponse;
import com.example.demogrpc.grpc.CityCore.CityScoreRequestOuterClass;
import com.example.demogrpc.grpc.CityCore.CityScoreServiceGrpc;
import io.grpc.Status;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Service
@Slf4j
public class CityScoreService {
    @Autowired
    private ChannelManager channelManager;

    private CityScoreServiceGrpc.CityScoreServiceBlockingStub cityScoreStub;

    @PostConstruct
    void init() {
        cityScoreStub = CityScoreServiceGrpc.newBlockingStub(channelManager.getChannel2());
    }

    public BaseResponse.Response getCityScore() {
        try {
            return cityScoreStub.calculateCityScore(
                    CityScoreRequestOuterClass.CityScoreRequest.newBuilder()
                            .setCityCode(100)
                            .build());
        } catch (Exception e) {
            Status status = Status.fromThrowable(e);
            System.out.println(status.getCode() + " : " + status.getDescription());
            throw new RuntimeException(status.getDescription());
        }
    }

    @PreDestroy
    public void teardown() {
        channelManager.getChannel2().shutdown();
    }
}
