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
        System.out.println("Se hace la peticion al Service desde el Controller de listar los juegos");
        return usuariosService.MostrarUsuarios();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> MostrarUsuarioPorId(@PathVariable int id){
        try{
            System.out.println("Se hace la peticion al Service desde el Controller de buscar juego con id: "+ id);
            Usuarios usuario = usuariosService.BuscarUsuario(id);
            System.out.println("Paso validacion de encontrar usuario dentro del Service");
            return ResponseEntity.ok(usuario);
        }catch(RuntimeException e){
            System.out.println("Error de validacion dentro del Service");
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<?> Agregar(@Valid @RequestBody Usuarios usuario, BindingResult result){
        if(result.hasErrors()){
            System.out.println("El Controller encontro un error en los datos ingresados");
            String mensaje = result.getFieldError().getDefaultMessage();
            return ResponseEntity.badRequest().body("Error de validación al asignar datos: " + mensaje);
       }
       try {
           System.out.println("Paso primera validacion del controller, pasando a validaciones del service");
           Usuarios NuevoUsuario = usuariosService.AgregarUsuario(usuario);
           System.out.println("Paso las Validaciones de agregar usuario dentro del Service");
           return ResponseEntity.ok(NuevoUsuario);
       } catch (RuntimeException e) {
            System.out.println("Error en validaciones del Service");
            return ResponseEntity.badRequest().body(e.getMessage());
       }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> Actualizar(@PathVariable int id, @Valid @RequestBody Usuarios usuario, BindingResult result){
        if(result.hasErrors()){
            System.out.println("El Controller encontro un error en los datos ingresados");
            String mensaje = result.getFieldError().getDefaultMessage();
            return ResponseEntity.badRequest().body("Error de validación al leer los datos: " + mensaje);
        }
        try {
            System.out.println("Paso primera validacion del controller, pasando a validaciones del service");
            usuario.Id = id;
            Usuarios UsuarioActualizado = usuariosService.ActualizarDatosUsuario(usuario);
            System.out.println("Paso las Validaciones de actualizar usuario dentro del Service");
            return ResponseEntity.ok(UsuarioActualizado);
        } catch (RuntimeException e) {
            System.out.println("Error de validacion dentro del Service");
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> Eliminar(@PathVariable int id){
        try {
            System.out.println("Se hace la peticion al Service desde el Controller de eliminar usuario");
            usuariosService.BorrarUsuario(id);
            System.out.println("Paso las Validaciones de eliminar usuario dentro del Service");
            return ResponseEntity.ok("El usuario con ID " + id + " ah sido eliminado exitosamente!");
        } catch (RuntimeException e) {
            System.out.println("Error de validacion dentro del Service");
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }
    @PostMapping("/{usuarioId}/biblioteca/{juegoId}")
    public ResponseEntity<?> agregarJuego(@PathVariable int usuarioId, @PathVariable int juegoId) {
        try {
            System.out.println("Se hace la peticion al Service desde el Controller de agregar juego a biblioteca de usuario");
            usuariosService.AgregarABiblioteca(usuarioId, juegoId);
            System.out.println("Paso las Validaciones de agregar juego a biblioteca de usuario dentro del Service");
            return ResponseEntity.status(201).body("Juego añadido con éxito.");
        } catch (RuntimeException e) {
            System.out.println("Error de validacion dentro del Service");
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }

    @GetMapping("/{id}/biblioteca")
    public ResponseEntity<?> mostrarBiblioteca(@PathVariable int id) {
        try {
            System.out.println("Se hace la peticion al Service desde el Controller de mostrar biblioteca de usuario");
            List<Juegos> biblioteca = usuariosService.MostrarBiblioteca(id);
            System.out.println("Paso las Validaciones de mostrar biblioteca de usuario dentro del Service");
            return ResponseEntity.ok(biblioteca);       
        } catch (RuntimeException e) {
            System.out.println("Error de validacion dentro del Service");
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }
    
}
