package br.dev.magliano.productjpa.controller;

import br.dev.magliano.productjpa.controller.dto.ProdutoDetalhesDTO;
import br.dev.magliano.productjpa.controller.dto.ProdutoForm;
import br.dev.magliano.productjpa.entity.Produto;
import br.dev.magliano.productjpa.entity.Usuario;
import br.dev.magliano.productjpa.repository.ProdutoRepository;
import br.dev.magliano.productjpa.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/produto")
public class ProdutoController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    @GetMapping
    public ResponseEntity<List<ProdutoDetalhesDTO>> listaTodos(){
        List<Produto> produtoList = produtoRepository.findAll();

        List<ProdutoDetalhesDTO> outputList = produtoList.stream()
                .map(Produto::toDetalhesDTO)
                .collect(Collectors.toList());

        return ResponseEntity.ok(outputList);
    }

    @PostMapping("/{idUsuario}")
    @Transactional
    public ResponseEntity cadastraProduto(@PathVariable("idUsuario") Long idUsuario,
                                          @RequestBody @Valid ProdutoForm produtoForm) {

        Usuario usuario = usuarioRepository.findById(idUsuario).orElseThrow(EntityNotFoundException::new);
        Produto produto = produtoForm.toEntity(usuario);


        produtoRepository.save(produto);

        ProdutoDetalhesDTO produtoDetalhesDTO = produto.toDetalhesDTO();

        return ResponseEntity.ok(produtoDetalhesDTO);
    }

}
