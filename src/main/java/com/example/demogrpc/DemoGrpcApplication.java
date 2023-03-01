package com.example.demogrpc;

import com.example.demogrpc.server.ServerManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.core.context.SecurityContext;

import java.security.Security;

@Slf4j
@SpringBootApplication
public class DemoGrpcApplication implements CommandLineRunner {
    private ServerManager serverManager;

    @Autowired
    DemoGrpcApplication(ServerManager serverManager) {
        this.serverManager = serverManager;
    }
    public static void main(String[] args) {
        SpringApplication.run(DemoGrpcApplication.class, args);
    }
    @Override
    public void run(String... args) throws Exception {
        log.info("Start proto server!");


        serverManager.getServer2().start();
//        serverManager.getServer2().awaitTermination();

        serverManager.getServer1().start();
//        serverManager.getServer1().awaitTermination();

    }
}
