package com.vapor.plataforma.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.vapor.plataforma.model.Juegos;


@Repository
public class JuegosRepository {

    public List<Juegos> ListaJuegos = new ArrayList<>();

    public List<Juegos> MostrarJuegos(){
        return ListaJuegos;
    }

    public String BuscarJuego(int id){
        for(Juegos juego : ListaJuegos){
            if(juego.getId()==id){
                return "El juego es: " + juego;
            }else{
                return "No se encontro el juego";
            }
        }
        return null;
    }

    public Juegos AñadirJuego(Juegos juego){
        ListaJuegos.add(juego);
        return juego;
    }

    public String EliminarJuego(int id){


        return "Juego borrado";
    }
    

    

}
