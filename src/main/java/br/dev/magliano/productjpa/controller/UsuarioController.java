package br.dev.magliano.productjpa.controller;

import br.dev.magliano.productjpa.controller.dto.UsuarioDto;
import br.dev.magliano.productjpa.controller.dto.UsuarioForm;
import br.dev.magliano.productjpa.entity.Usuario;
import br.dev.magliano.productjpa.repository.UsuarioRepository;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    UsuarioRepository usuarioRepository;

    @PostMapping("/")
    @Transactional
    public ResponseEntity cadastra(@RequestBody UsuarioForm usuarioForm){

        Usuario novoUsuario = usuarioForm.toEntity();
        usuarioRepository.save(novoUsuario);

        return ResponseEntity.ok(novoUsuario.toDto());
    }

    @GetMapping("/")
    //@PutMapping
    //@PatchMapping("/") = @RequestMapping(method = RequestMethod.PATCH, value = "/")
    //@RequestMapping(value = "/",method = RequestMethod.GET)
    public ResponseEntity listaTodos(){

        List<Usuario> usuarios = usuarioRepository.findAll();
        List<UsuarioDto> usuarioDtoList = usuarios.stream().map(Usuario::toDto).collect(Collectors.toList());

        return ResponseEntity.ok(usuarioDtoList);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity removeUsuario(@PathVariable("id") Long idUsuario){

        usuarioRepository.deleteById(idUsuario);
        return ResponseEntity.ok().build();
    }
}
