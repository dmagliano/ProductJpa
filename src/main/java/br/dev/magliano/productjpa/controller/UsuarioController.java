package br.dev.magliano.productjpa.controller;

import br.dev.magliano.productjpa.controller.dto.UsuarioDto;
import br.dev.magliano.productjpa.controller.dto.UsuarioForm;
import br.dev.magliano.productjpa.entity.Usuario;
import br.dev.magliano.productjpa.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    UsuarioRepository usuarioRepository;

    @PostMapping
    @Transactional
    public ResponseEntity<UsuarioDto> cadastra(@RequestBody UsuarioForm usuarioForm){

        Usuario novoUsuario = usuarioForm.toEntity();
        usuarioRepository.save(novoUsuario);

        return ResponseEntity.ok(novoUsuario.toDto());
    }
}
