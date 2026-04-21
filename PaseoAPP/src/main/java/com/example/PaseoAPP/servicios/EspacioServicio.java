package com.example.PaseoAPP.servicios;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.PaseoAPP.modelos.Espacio;
import com.example.PaseoAPP.modelos.Reserva;
import com.example.PaseoAPP.repositorios.IRepositorioEspacio;
import com.example.PaseoAPP.repositorios.IRepositorioReserva;

@Service
public class EspacioServicio {
  @Autowired
  private IRepositorioEspacio repositorioEspacio;

  public Espacio guardarEspacioEnBD(Espacio datosEspacio){

    if (repositorioEspacio.findById(datosEspacio.getId()).isPresent()) {
      throw new ResponseStatusException(
        HttpStatus.BAD_REQUEST, "El id que ingresaste ya existe en la base de datos"
      );
    }

    if (datosEspacio.getNombre().isEmpty() || datosEspacio.getNombre().isBlank()) {
      throw new ResponseStatusException(
        HttpStatus.BAD_REQUEST, "El nombre no puede estar vacio"
      );
    }
    if(datosEspacio.getAforo()<=0){
     throw new ResponseStatusException(
        HttpStatus.BAD_REQUEST, "El aforo debe ser mayor a 0"
       );
  }
   return this.repositorioEspacio.save(datosEspacio);
  
   }

   public Espacio modificarEspacioEnBD(Espacio datosEspacio, UUID id){

    Optional<Espacio> espacio_que_estoy_buscando=this.repositorioEspacio.findById(id);
    if (espacio_que_estoy_buscando.isEmpty()) {
      throw new ResponseStatusException(
        HttpStatus.NOT_FOUND,
        "El espacio que quieres editar no existe"
      );
    } 
    Espacio espacio_que_encontre=espacio_que_estoy_buscando.get();

    if (datosEspacio.getNombre().isEmpty() || datosEspacio.getNombre().isBlank()) {
      throw new ResponseStatusException(
        HttpStatus.BAD_REQUEST,
        "Revisa el nombre ingresado"
      );
    }

    espacio_que_encontre.setNombre(datosEspacio.getNombre());
    return this.repositorioEspacio.save(espacio_que_encontre);

    
    }

  public void eliminarEspacioEnBD(UUID id) {

    if (!repositorioEspacio.existsById(id)) {
        throw new ResponseStatusException(
            HttpStatus.NOT_FOUND,
            "Espacio no encontrado con ID: " + id
        );
    }

    repositorioEspacio.deleteById(id);
}

   public List<Espacio> buscarEspacioEnBD(){
    List<Espacio> espacioEncontrados=this.repositorioEspacio.findAll();
   return espacioEncontrados;
    }


}