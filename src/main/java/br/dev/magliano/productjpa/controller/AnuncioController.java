package br.dev.magliano.productjpa.controller;

import br.dev.magliano.productjpa.controller.dto.AnuncioDto;
import br.dev.magliano.productjpa.controller.dto.AnuncioForm;
import br.dev.magliano.productjpa.entity.AnuncioProduto;
import br.dev.magliano.productjpa.entity.Produto;
import br.dev.magliano.productjpa.repository.AnuncioRepository;
import br.dev.magliano.productjpa.repository.ProdutoRepository;
import java.util.List;
import java.util.stream.Collectors;
import javax.persistence.EntityExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/anuncios")
public class AnuncioController {

  @Autowired
  ProdutoRepository produtoRepository;

  @Autowired
  AnuncioRepository anuncioRepository;

  @PostMapping
  @Transactional
  public ResponseEntity cadastra(@RequestBody AnuncioForm novoAnuncio) {

    Produto produto = produtoRepository.findById(novoAnuncio.getIdProduto()).orElseThrow(
        EntityExistsException::new);

    List<AnuncioProduto> anunciosNaData =
        anuncioRepository.findAllByProdutoIdAndDataComecoBetweenOrDataFimBetween(produto.getId(),
            novoAnuncio.getDataComeco(), novoAnuncio.getDataFim(), novoAnuncio.getDataComeco(), novoAnuncio.getDataFim());

    if (!anunciosNaData.isEmpty()){
      return ResponseEntity.unprocessableEntity().build();
    }

    AnuncioProduto anuncio = new AnuncioProduto(produto, novoAnuncio.getDataComeco(), novoAnuncio.getDataFim());

    anuncioRepository.save(anuncio);

    return ResponseEntity.ok(anuncio.toDto());

  }

  @GetMapping
  public ResponseEntity busca(){

    List<AnuncioProduto> anuncioProdutos = anuncioRepository.findAll();

    List<AnuncioDto> dtoSaida = anuncioProdutos.stream().map(AnuncioProduto::toDto).collect(
        Collectors.toList());

    return ResponseEntity.ok(dtoSaida);
  }

}
