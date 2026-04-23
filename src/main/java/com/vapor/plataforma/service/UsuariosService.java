package com.vapor.plataforma.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vapor.plataforma.model.Usuarios;
import com.vapor.plataforma.repository.UsuariosRepository;

@Service
public class UsuariosService {

    @Autowired
    public UsuariosRepository usuariosRepository;

    public List<Usuarios> MostrarUsuarios(){
        return usuariosRepository.findAll();
    }
    public Usuarios BuscarUsuario(int id){
        return usuariosRepository.findById(id).orElseThrow(() -> new RuntimeException("No se encontro al usuario con el ID: " + id));
    }
    
    public Usuarios AgregarUsuario(Usuarios usuario){
        if (usuario == null){
            throw new RuntimeException("Los Datos del usuario no pueden estar vacios");
        }
        if  (!usuariosRepository.existsById(usuario.Id)){
            throw new RuntimeException("Ya existe un usuario con este Id");
        }
        if (usuario.Id != 0){
            throw new RuntimeException("La id al registrar un nuevo usuario tiene que ser 0");
        }
        if (usuario.Edad < 18){
            throw new RuntimeException("El usuario no puede ser menor de edad");
        }
        return usuariosRepository.save(usuario);
    }

}
