package com.starter.infra.config;

import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.core.DefaultDockerClientConfig;
import com.github.dockerjava.core.DockerClientBuilder;
import com.github.dockerjava.httpclient5.ApacheDockerHttpClient;
import io.kubernetes.client.openapi.ApiClient;
import io.kubernetes.client.openapi.apis.AppsV1Api;
import io.kubernetes.client.openapi.apis.CoreV1Api;
import io.kubernetes.client.util.Config;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.time.Duration;

@Configuration
public class InfraConfig {
    @Value("${infra.docker.host:unix:///var/run/docker.sock}")
    String dockerHost;
    @Value("${infra.docker.timeout:30s}")
    Duration timeout;
    @Value("${infra.kube.config:}")
    String kubePath;

    @Bean
    DockerClient dockerClient() {
        var cfg = DefaultDockerClientConfig.createDefaultConfigBuilder().withDockerHost(dockerHost).build();
        var http = new ApacheDockerHttpClient.Builder().dockerHost(cfg.getDockerHost())
                .connectionTimeout(Duration.ofSeconds(10)).responseTimeout(timeout).build();
        return DockerClientBuilder.getInstance(cfg).withDockerHttpClient(http).build();
    }

    @Bean
    ApiClient kubeClient() throws Exception {
        var c = kubePath.isEmpty() ? (Config.fromCluster()) : Config.fromConfig(kubePath);
        io.kubernetes.client.openapi.Configuration.setDefaultApiClient(c);
        return c;
    }

    @Bean
    CoreV1Api coreV1(ApiClient c) {
        return new CoreV1Api(c);
    }

    @Bean
    AppsV1Api appsV1(ApiClient c) {
        return new AppsV1Api(c);
    }
}