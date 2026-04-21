package com.example.PaseoAPP.controladores;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.PaseoAPP.modelos.Espacio;
import com.example.PaseoAPP.servicios.EspacioServicio;

@RestController
@RequestMapping("/paseoapi/v1/espacios")
public class EspacioControlador {

    @Autowired
    EspacioServicio servicio;

    // Guardar
    @PostMapping
    public ResponseEntity<Espacio> controlarGuardado(@RequestBody Espacio datos){
        return ResponseEntity.status(HttpStatus.CREATED)
            .body(this.servicio.guardarEspacioEnBD(datos));
    }

    // Modificar
    @PostMapping("/{id}")
    public ResponseEntity<Espacio> controlarModificado(@RequestBody Espacio datos, @PathVariable UUID id){
        return ResponseEntity.status(HttpStatus.OK)
            .body(this.servicio.modificarEspacioEnBD(datos, id));
    }

    // Eliminar
    @DeleteMapping("/{id}")
    public ResponseEntity<String> controlarBorrado(@PathVariable UUID id){
        this.servicio.eliminarEspacioEnBD(id);
        return ResponseEntity.ok("Espacio eliminado correctamente");
    }

    // Listar
    @GetMapping
    public ResponseEntity<?> controlarListado(){
        return ResponseEntity.status(HttpStatus.OK)
          .body(this.servicio.buscarEspacioEnBD());
    }
}