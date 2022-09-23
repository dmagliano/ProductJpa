package br.dev.magliano.productjpa.controller;

import br.dev.magliano.productjpa.controller.dto.AvaliacaoForm;
import br.dev.magliano.productjpa.entity.Avaliacao;
import br.dev.magliano.productjpa.entity.Produto;
import br.dev.magliano.productjpa.entity.Usuario;
import br.dev.magliano.productjpa.repository.AvaliacaoRepository;
import br.dev.magliano.productjpa.repository.ProdutoRepository;
import br.dev.magliano.productjpa.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/avaliacao")
public class AvaliacaoController {

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    ProdutoRepository produtoRepository;

    @Autowired
    AvaliacaoRepository avaliacaoRepository;

    @GetMapping("/{idProduto}")
    public ResponseEntity avaliacoesProduto(@PathVariable("idProduto") Long idProduto){
        List<Avaliacao> avaliacaoList = avaliacaoRepository.findAvaliacaoByProdutoId(idProduto);

        return ResponseEntity.ok(avaliacaoList.stream().map(Avaliacao::toDetalhesDTO));
    }


    @PostMapping("/{idProduto}")
    @Transactional
    public ResponseEntity cadastraAvaliacao(@PathVariable("idProduto") Long idProduto,
                                            @RequestParam("userId") Long idUsuario,
                                            @RequestBody @Valid AvaliacaoForm avaliacaoform) {

        Usuario usuario = usuarioRepository.findById(idUsuario).orElseThrow(EntityNotFoundException::new);
        Produto produto = produtoRepository.findById(idProduto).orElseThrow(EntityNotFoundException::new);

        Avaliacao avaliacao = new Avaliacao(avaliacaoform.getAvaliacao(), produto, usuario);
        produto.adicionaAvaliacao(avaliacao);

        avaliacaoRepository.save(avaliacao);

        //produtoRepository.saveAndFlush(produto);

        return ResponseEntity.ok(avaliacao.toAvaliacaoDTO());
    }


    @DeleteMapping("/{idAvaliacao}")
    @Transactional
    public ResponseEntity deletaAvaliacao(@PathVariable("idAvaliacao") Long idAvaliacao,
                                          @RequestParam("userId") Long idUsuario) {

        Avaliacao avaliacao = avaliacaoRepository.findById(idAvaliacao).orElseThrow(EntityNotFoundException::new);

        Long idUsuarioCriador = avaliacao.getUsuario().getId();

        if (idUsuario == idUsuarioCriador){
            avaliacaoRepository.delete(avaliacao);
            return ResponseEntity.ok().build();
        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

}
