package com.vapor.plataforma.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vapor.plataforma.model.Juegos;
import com.vapor.plataforma.model.Usuarios;
import com.vapor.plataforma.service.UsuariosService;

import jakarta.validation.Valid;



@RestController
@RequestMapping("/usuarios")
public class UsuariosController {

    @Autowired
    public UsuariosService usuariosService;


    @GetMapping
    public List<Usuarios> MostrarUsuarios(){
        return usuariosService.MostrarUsuarios();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> MostrarUsuarioPorId(@PathVariable int id){
        try{
            Usuarios usuario = usuariosService.BuscarUsuario(id);
            return ResponseEntity.ok(usuario);
        }catch(RuntimeException e){
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<?> Agregar(@Valid @RequestBody Usuarios usuario, BindingResult result){
        if(result.hasErrors()){
            String mensaje = result.getFieldError().getDefaultMessage();
            return ResponseEntity.badRequest().body("Error de validación al asignar datos: " + mensaje);
       }
       try {
           Usuarios NuevoUsuario = usuariosService.AgregarUsuario(usuario);
           return ResponseEntity.ok(NuevoUsuario);
       } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
       }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> Actualizar(@PathVariable int id, @Valid @RequestBody Usuarios usuario, BindingResult result){
        if(result.hasErrors()){
            String mensaje = result.getFieldError().getDefaultMessage();
            return ResponseEntity.badRequest().body("Error de validación al asignar datos: " + mensaje);
        }
        try {
            usuario.Id = id;
            Usuarios UsuarioActualizado = usuariosService.ActualizarDatosUsuario(usuario);
            return ResponseEntity.ok(UsuarioActualizado);
        } catch (RuntimeException e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> Eliminar(@PathVariable int id){
        try {
            usuariosService.BorrarUsuario(id);
            return ResponseEntity.ok("El usuario con ID " + id + " ah sido eliminado exitosamente!");
        } catch (RuntimeException e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }
    @PostMapping("/{usuarioId}/biblioteca/{juegoId}")
    public ResponseEntity<?> agregarJuego(@PathVariable int usuarioId, @PathVariable int juegoId) {
        try {
            usuariosService.AgregarABiblioteca(usuarioId, juegoId);
            return ResponseEntity.status(201).body("Juego añadido con éxito.");
        } catch (RuntimeException e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }

    @GetMapping("/{id}/biblioteca")
    public ResponseEntity<?> mostrarBiblioteca(@PathVariable int id) {
        try {
            List<Juegos> biblioteca = usuariosService.MostrarBiblioteca(id);
            return ResponseEntity.ok(biblioteca);       
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }
    
}
