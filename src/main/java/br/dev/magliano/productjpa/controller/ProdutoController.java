package br.dev.magliano.productjpa.controller;

import br.dev.magliano.productjpa.controller.dto.ProdutoDetalhesDTO;
import br.dev.magliano.productjpa.controller.dto.ProdutoForm;
import br.dev.magliano.productjpa.entity.Produto;
import br.dev.magliano.productjpa.entity.Usuario;
import br.dev.magliano.productjpa.entity.Vendedor;
import br.dev.magliano.productjpa.repository.ProdutoRepository;
import br.dev.magliano.productjpa.repository.UsuarioRepository;
import br.dev.magliano.productjpa.repository.VendedoRepository;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/produto")
public class ProdutoController {

  @Autowired
  private VendedoRepository vendedoRepository;

  @Autowired
  private UsuarioRepository usuarioRepository;

  @Autowired
  private ProdutoRepository produtoRepository;

  @GetMapping
  public ResponseEntity<List<ProdutoDetalhesDTO>> listaTodos() {
    List<Produto> produtoList = produtoRepository.findAll();
    List<ProdutoDetalhesDTO> outputList = produtoList.stream()
        .map(Produto::toDetalhesDTO)
        .collect(Collectors.toList());

    return ResponseEntity.ok(outputList);
  }

  @GetMapping("/datas/")
  public ResponseEntity<List<ProdutoDetalhesDTO>> listaPorDatas(
      @RequestParam(name = "dataComeco") LocalDateTime dataComeco,
      @RequestParam(name = "dataFim") LocalDateTime dataFim) {

    List<Produto> produtoList = produtoRepository.findProdutoByDate(dataFim, dataComeco);

    List<ProdutoDetalhesDTO> outputList = produtoList.stream()
        .map(Produto::toDetalhesDTO)
        .collect(Collectors.toList());

    return ResponseEntity.ok(outputList);
  }

  @PostMapping("/{idVendedor}")
  @Transactional
  public ResponseEntity cadastraProduto(@PathVariable("idVendedor") Long idVendedor,
      @RequestBody @Valid ProdutoForm produtoForm) {

    Vendedor vendedor = vendedoRepository.findById(idVendedor)
        .orElseThrow(EntityNotFoundException::new);

    Usuario usuario = vendedor.getUsuarioVendedor();
    Produto produto = produtoForm.toEntity(usuario);

    produtoRepository.save(produto);

    ProdutoDetalhesDTO produtoDetalhesDTO = produto.toDetalhesDTO();

    return ResponseEntity.ok(produtoDetalhesDTO);
  }


}
