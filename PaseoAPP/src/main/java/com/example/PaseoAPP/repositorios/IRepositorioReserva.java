package com.example.PaseoAPP.repositorios;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.PaseoAPP.modelos.Reserva;
import com.example.PaseoAPP.modelos.Usuario;

@Repository
public interface IRepositorioReserva extends JpaRepository<Reserva, UUID>{
  
}
