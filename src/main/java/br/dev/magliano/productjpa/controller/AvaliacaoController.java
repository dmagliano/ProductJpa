package br.dev.magliano.productjpa.controller;

import br.dev.magliano.productjpa.controller.dto.AvaliacaoDTO;
import br.dev.magliano.productjpa.controller.dto.AvaliacaoForm;
import br.dev.magliano.productjpa.controller.dto.AvaliacaoListProdutoDto;
import br.dev.magliano.productjpa.controller.dto.AvaliacaoListUsuarioDto;
import br.dev.magliano.productjpa.controller.dto.AvaliacaoProdutoDTO;
import br.dev.magliano.productjpa.entity.Avaliacao;
import br.dev.magliano.productjpa.entity.Produto;
import br.dev.magliano.productjpa.entity.Usuario;
import br.dev.magliano.productjpa.repository.AvaliacaoRepository;
import br.dev.magliano.productjpa.repository.ProdutoRepository;
import br.dev.magliano.productjpa.repository.UsuarioRepository;
import java.util.List;
import java.util.stream.Collectors;
import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
  public ResponseEntity avaliacoesProduto(@PathVariable("idProduto") Long idProduto) {

    Produto produto = produtoRepository.findById(idProduto).orElseThrow(EntityNotFoundException::new);

    List<Avaliacao> avaliacaoList = avaliacaoRepository.findAvaliacaoByProdutoId(idProduto);

    if (!avaliacaoList.isEmpty()) {

      List<AvaliacaoDTO> avaliacaoDTOList = avaliacaoList.stream().map(Avaliacao::toAvaliacaoDTO)
          .collect(Collectors.toList());

      return ResponseEntity
          .ok(new AvaliacaoListProdutoDto(produto.getNome(), produto.getId(), avaliacaoDTOList));

    }

    return ResponseEntity.ok(avaliacaoList);
  }


  @PostMapping("/{idProduto}")
  @Transactional
  public ResponseEntity cadastraAvaliacao(@PathVariable("idProduto") Long idProduto,
      @RequestParam("userId") Long idUsuario,
      @RequestBody @Valid AvaliacaoForm avaliacaoform) {

    Usuario usuario = usuarioRepository.findById(idUsuario)
        .orElseThrow(EntityNotFoundException::new);
    Produto produto = produtoRepository.findById(idProduto)
        .orElseThrow(EntityNotFoundException::new);

    if (produto.getVendedor().getId().equals(usuario.getId())) {
      return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE)
          .body("O vendedor não pode castrar avaliações no próprio produto");
    }

    Avaliacao avaliacao = new Avaliacao(avaliacaoform.getAvaliacao(), produto, usuario);
    produto.adicionaAvaliacao(avaliacao);

    avaliacaoRepository.save(avaliacao);

    return ResponseEntity.ok(avaliacao.toAvaliacaoProdutoDTO());
  }


  @DeleteMapping("/{idAvaliacao}")
  @Transactional
  public ResponseEntity deletaAvaliacao(@PathVariable("idAvaliacao") Long idAvaliacao,
      @RequestParam("userId") Long idUsuario) {

    Avaliacao avaliacao = avaliacaoRepository.findById(idAvaliacao)
        .orElseThrow(EntityNotFoundException::new);

    Long idUsuarioCriador = avaliacao.getUsuario().getId();

    if (idUsuario == idUsuarioCriador) {
      avaliacaoRepository.delete(avaliacao);
      return ResponseEntity.ok().build();
    }

    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
  }

  @GetMapping("/usuario/{id}")
  public ResponseEntity avaliacaoUsuario(@PathVariable("id") Long idUsuario) {

    Usuario usuario = usuarioRepository.findById(idUsuario).orElseThrow(EntityNotFoundException::new);

    List<Avaliacao> avaliacaoList = avaliacaoRepository.findAvaliacaoByUsuarioId(idUsuario);
    if (!avaliacaoList.isEmpty()) {
      List<AvaliacaoProdutoDTO> avaliacaoProdutoDTOS = avaliacaoList.stream()
          .map(Avaliacao::toAvaliacaoProdutoDTO).collect(Collectors.toList());

      AvaliacaoListUsuarioDto avaliacaoListUsuarioDto = new AvaliacaoListUsuarioDto(usuario.getId(),
          usuario.getUsername(), avaliacaoProdutoDTOS);

      return ResponseEntity.ok(avaliacaoListUsuarioDto);
    }

    return ResponseEntity.ok(avaliacaoList);
  }

}
