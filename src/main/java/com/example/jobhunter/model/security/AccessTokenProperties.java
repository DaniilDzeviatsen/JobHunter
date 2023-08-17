package com.example.jobhunter.model.security;

import lombok.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.time.Duration;

@ConfigurationProperties(prefix = "postgres.access-token")
@Value
public class AccessTokenProperties {

    String secret;

    Duration timeToLive;
}
