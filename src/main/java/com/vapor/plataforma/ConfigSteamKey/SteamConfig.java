package com.vapor.plataforma.ConfigSteamKey;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

@Configuration
@ConfigurationProperties(prefix = "steam")
@Data
public class SteamConfig {

    private String apiKey;
    
    private String apiUrl;

}
