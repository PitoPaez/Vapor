package com.vapor.plataforma.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
