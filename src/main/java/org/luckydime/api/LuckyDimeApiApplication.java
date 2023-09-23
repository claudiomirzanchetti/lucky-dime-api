package org.luckydime.api;

import org.luckydime.api.config.DerbyNetworkServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "org.luckydime.api")
public class LuckyDimeApiApplication {
	public static void main(String[] args) throws Exception {
		DerbyNetworkServer.start();
		SpringApplication.run(LuckyDimeApiApplication.class, args);
	}
}
