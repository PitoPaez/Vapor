package com.vapor.plataforma.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vapor.plataforma.model.Juegos;
import com.vapor.plataforma.model.Usuarios;
import com.vapor.plataforma.repository.JuegosRepository;
import com.vapor.plataforma.repository.UsuariosRepository;

@Service
public class UsuariosService {

    @Autowired
    public UsuariosRepository usuariosRepository;

    @Autowired
    public JuegosRepository juegosRepository;

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

    public Usuarios ActualizarDatosUsuario(Usuarios usuario){
        if (!usuariosRepository.existsById(usuario.Id)){
            throw new RuntimeException("No se encontro al usuario con el ID :" + usuario.Id);
        }
        if (usuario.Id != 0){
            throw new RuntimeException("La id al registrar un nuevo usuario tiene que ser 0");
        }
        if (usuario.Edad < 18){
            throw new RuntimeException("El usuario no puede ser menor de edad");
        }
        return usuariosRepository.save(usuario);
    }

    public void BorrarUsuario(int id){    
        if (!usuariosRepository.existsById(id)){
            throw new RuntimeException ("No se encotro al usuario con ID: " + id);
        }
        usuariosRepository.deleteById(id);
        
    }


    public void AgregarABiblioteca(int usuarioId, int juegoId){
        
        Usuarios usuario = usuariosRepository.findById(usuarioId).orElseThrow(() -> new RuntimeException("No se encontró al usuario con ID: " + usuarioId));
        Juegos juego = juegosRepository.findById(juegoId).orElseThrow(() -> new RuntimeException("No se encontró el juego con ID: " + juegoId));

        if (usuario.getBiblioteca().contains(juego)){
            throw new RuntimeException("El usuario ya tiene este juego en su biblioteca");
        }
        usuario.getBiblioteca().add(juego);
        usuariosRepository.save(usuario);

    }

    public List<Juegos> MostrarBiblioteca(int id){

        Usuarios usuario = usuariosRepository.findById(id).orElseThrow(() -> new RuntimeException("No se encontro al usuario con el ID: " + id));
        return usuario.getBiblioteca();

    }

}
