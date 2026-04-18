package com.vapor.plataforma.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vapor.plataforma.model.Juegos;

@Repository
public interface  JuegosRepository extends JpaRepository<Juegos, Integer> {
}
