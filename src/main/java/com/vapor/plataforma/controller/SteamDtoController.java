package com.vapor.plataforma.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vapor.plataforma.SteamDTO.SteamDTO;
import com.vapor.plataforma.service.SteamDtoService;



@RestController
@RequestMapping("/Perfil_Steam")
public class SteamDtoController {

    @Autowired
    public SteamDtoService steamDtoService;

    @GetMapping("/{steamId}")
    public ResponseEntity<?> getPerfil(@PathVariable String steamId) {
        try {
            SteamDTO.PlayerDTO perfil = steamDtoService.BuscarUsuarioPorID(steamId);
            return ResponseEntity.ok(perfil);
            
        } catch (RuntimeException e) {
            System.out.println("Error de validacion dentro del Service");
            return ResponseEntity.status(500).body(e.getMessage());
        }

    }
}