package com.vapor.plataforma.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.vapor.plataforma.ConfigSteamKey.SteamConfig;
import com.vapor.plataforma.SteamDTO.SteamDTO;

@Service
public class SteamDtoService {

    @Autowired
    @Qualifier("steamWebClient")
    private WebClient steamWebClient;
    
    @Autowired
    private SteamConfig steamConfig;

    public SteamDTO.PlayerDTO BuscarUsuarioPorID(String steamId) {

        if (steamConfig.getApiKey() == null) {
            throw new RuntimeException("La API Key de Steam no está configurada.");
        }

        SteamDTO usuario = steamWebClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/ISteamUser/GetPlayerSummaries/v0002/")
                        .queryParam("key", steamConfig.getApiKey())
                        .queryParam("steamids", steamId)
                        .build())
                .retrieve()
                .bodyToMono(SteamDTO.class)
                .block(); 

        if (usuario == null || usuario.getResponse() == null || usuario.getResponse().getPlayers().isEmpty()) {
            throw new RuntimeException("No se encontraro un usuario con el ID: " + steamId);
        }

        return usuario.getResponse().getPlayers().get(0);
    }

}
