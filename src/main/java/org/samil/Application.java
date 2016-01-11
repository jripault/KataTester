package org.samil;

import org.samil.internal.security.SandboxSecurityPolicy;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.security.Policy;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        Policy.setPolicy(new SandboxSecurityPolicy());
        System.setSecurityManager(new SecurityManager());
        SpringApplication.run(Application.class, args);
    }

}