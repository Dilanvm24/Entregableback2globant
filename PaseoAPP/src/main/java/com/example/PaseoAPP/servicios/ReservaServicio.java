package com.example.PaseoAPP.servicios;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.PaseoAPP.modelos.Reserva;
import com.example.PaseoAPP.repositorios.IRepositorioReserva;

@Service
public class ReservaServicio {

  @Autowired
  private IRepositorioReserva repositorioReserva;

  public Reserva guardarReservaEnBD(Reserva datos) {

    if (repositorioReserva.findById(null).isPresent()) {
      throw new ResponseStatusException(
          HttpStatus.CONFLICT, "El id que ingresaste ya existe en la base de datos");
    }

    if (datos.getTiempo() <= 60) {
      throw new ResponseStatusException(
          HttpStatus.BAD_REQUEST, "El tiempo debe ser mayor a 60 minutos");
    }

    if (datos.getId() == null) {
      throw new ResponseStatusException(
          HttpStatus.BAD_REQUEST, "El id no puede ser nulo");
    }
    return this.repositorioReserva.save(datos);
  }

  public Reserva modificarReservaEnBD(Reserva datos, UUID id) {
    Optional<Reserva> reserva_que_estoy_buscando=this.repositorioReserva.findById(id);
    if (reserva_que_estoy_buscando.isEmpty()) {
      throw new ResponseStatusException(
        HttpStatus.NOT_FOUND,
        "La reserva que quieres editar no existe"
      );
    }

    Reserva reserva_que_encontre=reserva_que_estoy_buscando.get();

    if (datos.getFecha() == null) {
      throw new ResponseStatusException(
        HttpStatus.BAD_REQUEST,
        "Revisa la fecha ingresada"
      );
    }  

    reserva_que_encontre.setFecha(datos.getFecha());
    return this.repositorioReserva.save(reserva_que_encontre);
      
  }

public void eliminarReservaEnBD(UUID id) {
    if (!repositorioReserva.existsById(id)) {
        throw new ResponseStatusException(
            HttpStatus.NOT_FOUND,
            "Reserva no encontrada con ID: " + id
        );
    }

    repositorioReserva.deleteById(id);
}

  public List<Reserva> buscarReservaEnBD() {
    List<Reserva> reservaEncontrada=this.repositorioReserva.findAll();
    return reservaEncontrada;
  }

}
