package com.vapor.plataforma.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.vapor.plataforma.model.Usuarios;


@Repository
public class UsuariosRepository {

    public List<Usuarios> ListaUsuarios = new ArrayList<>();

    public List<Usuarios> MostUsuarios(){
        return ListaUsuarios;
    }

}
