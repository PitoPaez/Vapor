package com.vapor.plataforma.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vapor.plataforma.model.Juegos;
import com.vapor.plataforma.service.JuegosService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/juegos")
public class JuegosController {

    @Autowired
    private JuegosService juegosService;

    @GetMapping
    public List<Juegos> Listar(){
        return juegosService.MostrarJuegos();
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<?> BuscarPorId(@PathVariable int id){
        try{
            Juegos juego = juegosService.BuscarPorId(id);
            return ResponseEntity.ok(juego);
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<?> Agregar(@Valid @RequestBody Juegos juego){
        try {
            Juegos juegoAgregado = juegosService.AgregarJuego(juego);
            return ResponseEntity.status(402).body(juegoAgregado);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> Actualizar(@PathVariable int id, @Valid @RequestBody Juegos juego){
        try {
            juego.Id = id;
            Juegos juegoActualizado = juegosService.ActualizarDatosJuego(juego);
            return ResponseEntity.ok(juegoActualizado);
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> Eliminar(@PathVariable int id){
        try {
            juegosService.EliminarJuego(id);
            return ResponseEntity.ok("El juego con ID " + id + " ah sido eliminado exitosamente!");
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }
    
}
