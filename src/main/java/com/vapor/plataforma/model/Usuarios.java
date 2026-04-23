package com.vapor.plataforma.model;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
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
@Table(name = "usuarios")
public class Usuarios {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int Id;

    @NotBlank(message="El usuario debe tener un nombre")
    public String Nombre;

    @NotBlank(message="El usuario tiene que tener correo")
    public String Correo;

    @NotBlank(message="El usuario debe tener un apodo")
    public String ApodoUsuario;

    @NotNull(message="Se debe ingresar la edad")
    public int Edad;

    @ManyToMany
    @JoinTable(name = "Biblioteca")
    public List<Juegos> ListaBiblioteca;     

}
