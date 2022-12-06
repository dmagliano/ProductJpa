package br.dev.magliano.productjpa.controller;

import br.dev.magliano.productjpa.entity.Usuario;
import br.dev.magliano.productjpa.entity.Vendedor;
import br.dev.magliano.productjpa.repository.UsuarioRepository;
import br.dev.magliano.productjpa.repository.VendedoRepository;
import javax.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/vendedor")
public class VendedorController {

  @Autowired
  VendedoRepository vendedoRepository;

  @Autowired
  UsuarioRepository usuarioRepository;

  public ResponseEntity cadastraVedendor(@PathVariable("id") Long idUsuario){

    Usuario usuario = usuarioRepository.findById(idUsuario).orElseThrow(EntityNotFoundException::new);
    Vendedor vendedor = new Vendedor(usuario);

    return ResponseEntity.ok().build();
  }

}
