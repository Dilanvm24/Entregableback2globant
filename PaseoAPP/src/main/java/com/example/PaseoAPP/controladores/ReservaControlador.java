package com.example.PaseoAPP.controladores;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.PaseoAPP.modelos.Reserva;
import com.example.PaseoAPP.servicios.ReservaServicio;

@RestController
@RequestMapping("/paseoapi/v1/reservas")
public class ReservaControlador {

    @Autowired
    ReservaServicio servicio;

    // Guardar
    @PostMapping
    public ResponseEntity<Reserva> controlarGuardado(@RequestBody Reserva datos){
        return ResponseEntity.status(HttpStatus.CREATED)
            .body(this.servicio.guardarReservaEnBD(datos));
    }

    // Modificar
    @PostMapping("/{id}")
    public ResponseEntity<Reserva> controlarModificado(@RequestBody Reserva datos, @PathVariable UUID id){
        return ResponseEntity.status(HttpStatus.OK)
            .body(this.servicio.modificarReservaEnBD(datos, id));
    }

    // Eliminar
    @DeleteMapping("/{id}")
    public ResponseEntity<String> controlarBorrado(@PathVariable UUID id){
        this.servicio.eliminarReservaEnBD(id);
        return ResponseEntity.ok("Reserva eliminada correctamente");
    }

    // Listar
    @GetMapping
    public ResponseEntity<?> controlarListado(){
        return ResponseEntity.status(HttpStatus.OK)
        .body(this.servicio.buscarReservaEnBD());
    }
}