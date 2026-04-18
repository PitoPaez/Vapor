package com.vapor.plataforma.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vapor.plataforma.model.Juegos;
import com.vapor.plataforma.repository.JuegosRepository;

@Service
public class JuegosService {

    @Autowired
    private JuegosRepository juegosRepository;

    public List<Juegos> MostrarJuegos(){
        return juegosRepository.findAll();
    }

}
