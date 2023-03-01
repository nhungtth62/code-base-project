package com.example.demogrpc.client.Controller;

import com.example.demogrpc.client.Service.CityScoreService;
import com.example.demogrpc.client.Service.HelloService;
import com.example.demogrpc.grpc.CityCore.CityCoreResponse;
import com.google.protobuf.InvalidProtocolBufferException;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class TestController {

    private CityScoreService cityScoreService;

    private HelloService helloService;

    @GetMapping("/test/hello")
    public String getHello() {
        return helloService.getHelloResponse().getGreeting();
    }

    @GetMapping("/test/city-score")
    public int getCityScore() throws InvalidProtocolBufferException {
        return cityScoreService.getCityScore().getData().unpack(CityCoreResponse.CityScoreResponse.class).getScore();
    }
}
