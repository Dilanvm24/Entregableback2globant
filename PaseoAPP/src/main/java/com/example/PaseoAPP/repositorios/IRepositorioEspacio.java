package com.example.PaseoAPP.repositorios;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.web.server.ResponseStatusException;

import com.example.PaseoAPP.modelos.Espacio;

@Repository
public interface IRepositorioEspacio extends JpaRepository<Espacio, UUID>{

}
