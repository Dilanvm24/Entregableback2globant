package com.example.PaseoAPP.controladores;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.PaseoAPP.modelos.Usuario;
import com.example.PaseoAPP.servicios.UsuarioServicio;

@RestController
@RequestMapping("/paseoapi/v1/usuarios")
public class UsuarioControlador {

  //Por cada servicio ofrecido
  //configuro 1 funcion controladora

  @Autowired
  UsuarioServicio servicio;

  //funcion para controlar el guardado

  @PostMapping
  public ResponseEntity<Usuario> controlarGuardado(@RequestBody Usuario datos){
    return ResponseEntity.status(HttpStatus.CREATED)
    .body(this.servicio.guardarUsuarioEnBD(datos));
  }
  //funcion para controlar las modificaciones
  @PostMapping("/{id}")
  public ResponseEntity<Usuario> controlarModificado(@RequestBody Usuario datos, @PathVariable UUID id){
    return ResponseEntity.status(HttpStatus.OK)
    .body(this.servicio.modificarUsuarioEnBD(datos, id));
  }
  //funcion para controlar el borrado
 @DeleteMapping("/{id}")
public ResponseEntity<String> controlarBorrado(@PathVariable UUID id){
    this.servicio.eliminarUsuarioEnBD(id);
    return ResponseEntity.ok("Usuario eliminado correctamente");
}
  //funcion para controlar el listar
  @GetMapping
  public ResponseEntity<?> controlarListado(){
    return ResponseEntity.status(HttpStatus.OK)
    .body(this.servicio.buscarUsuariosEnBD());
  }


}
