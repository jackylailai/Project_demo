package org.example;

import org.example.netty.server.NettyServer;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class NettyServerRunner implements CommandLineRunner {
    @Override
    public void run(String... args) {
        try {
            NettyServer.main(args);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
