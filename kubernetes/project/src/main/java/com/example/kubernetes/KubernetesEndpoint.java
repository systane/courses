package com.example.kubernetes;

import lombok.AllArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class KubernetesEndpoint {

    private final Environment environment;

    @GetMapping(value = "/helloKubernetes")
    public String helloKubernetes() {
        String port = environment.getProperty("local.server.port");
        return "Hello Kubernetes from port " + port + " :)";
    }
}
