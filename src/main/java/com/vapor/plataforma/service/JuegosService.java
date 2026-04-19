package com.vapor.plataforma.service;

import java.time.LocalDate;
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

    public Juegos AgregarJuego(Juegos juego){
        if (juego == null){
            throw new RuntimeException("Los datos del juego no pueden estar vacios"); 
        }
        if (juego.Calificacion < 0 || juego.Calificacion > 100) {
            throw new RuntimeException("La calificación debe ser del 0 al 100.");
        }
        if (juego.getFechaLanzamiento().isAfter(LocalDate.now())) {
            throw new RuntimeException("La fecha de lanzamiento no puede estar en un tiempo futuro.");
        }
        juego.Id = 0;
        return juegosRepository.save(juego);
    }
        
    public Juegos BuscarPorId(int id){
        return juegosRepository.findById(id).orElseThrow(() -> new RuntimeException("No se encontro el juego con el ID: " + id));
    }

    public Juegos ActualizarDatosJuego(Juegos juego){
        if (!juegosRepository.existsById(juego.Id)) {
            throw new RuntimeException("No se puede actualizar: El juego con ID " + juego.Id + " no existe.");
        }
        if (juego.Calificacion < 0 || juego.Calificacion > 100) {
            throw new RuntimeException("La calificación debe ser del 0 al 100.");
        }
        if (juego.getFechaLanzamiento().isAfter(LocalDate.now())) {
            throw new RuntimeException("La fecha de lanzamiento no puede estar en un tiempo futuro.");
        }
        return juegosRepository.save(juego);
    }

    public void EliminarJuego(int id){
        if(!juegosRepository.existsById(id)){
            throw new RuntimeException("No se encontro el juego de ID: " + id);
        }
        juegosRepository.deleteById(id);
    }

}
