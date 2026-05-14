package com.vapor.plataforma.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.vapor.plataforma.ConfigSteamKey.SteamConfig;
import com.vapor.plataforma.SteamDTO.SteamDTO;

@Service
public class SteamDtoService {



    private final RestTemplate restTemplate;    
    private final SteamConfig steamConfig;

    public SteamDtoService(SteamConfig steamConfig) {
        this.steamConfig = steamConfig;
        this.restTemplate = new RestTemplate();
    }

    public SteamDTO.PlayerDTO obtenerResumenUsuario(String steamId) {
    String url = UriComponentsBuilder.newInstance() // Crea la instancia primero
        .scheme("https")
        .host("api.steampowered.com")
        .path("/ISteamUser/GetPlayerSummaries/v0002/")
        .queryParam("key", steamConfig.getApiKey())
        .queryParam("steamids", steamId)
        .build()
        .toUriString();
        
        SteamDTO usuario = restTemplate.getForObject(url, SteamDTO.class);

        if (usuario == null || usuario.getResponse() == null || usuario.getResponse().getPlayers().isEmpty()) {
            throw new RuntimeException("No se encontraron resultados en Steam para el ID: " + steamId);
        }
        return usuario.getResponse().getPlayers().get(0);
    }

}
