package com.example.PaseoAPP.modelos;

import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Reservas")
public class Reserva {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;
  private java.time.LocalDateTime fecha;
  private Integer tiempo;

  public Reserva() {
  }

  public Reserva(UUID id, LocalDateTime fecha, Integer tiempo) {
    this.id = id;
    this.fecha = fecha;
    this.tiempo = tiempo;
  }

  public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
    this.id = id;
  }

  public java.time.LocalDateTime getFecha() {
    return fecha;
  }

  public void setFecha(java.time.LocalDateTime fecha) {
    this.fecha = fecha;
  }

  public Integer getTiempo() {
    return tiempo;
  }

  public void setTiempo(Integer tiempo) {
    this.tiempo = tiempo;
  }
  
}
