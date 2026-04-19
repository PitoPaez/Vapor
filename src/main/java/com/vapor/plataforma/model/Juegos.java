package com.vapor.plataforma.model;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "juegos")
public class Juegos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int Id;

    @NotBlank(message="El juego no puede no tener nombre")
    public String NombreJuego;

    @NotNull(message="La fecha del juego no puede estar vacia")
    public LocalDate FechaLanzamiento;

    @NotNull(message="El juego debe tener clasificacion")
    public int Calificacion;

    @NotBlank(message="El juego debe tener descripcion")
    public String Descripcion;

}
