package br.dev.magliano.productjpa.controller.dto;

import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class AvaliacaoListProdutoDto {

  private String nomeProduto;

  private Long idProduto;

  private List<AvaliacaoDTO> avaliacaoList;

  public AvaliacaoListProdutoDto(String nomeProduto, Long idProduto,
      List<AvaliacaoDTO> avaliacaoList) {
    this.nomeProduto = nomeProduto;
    this.idProduto = idProduto;
    this.avaliacaoList = avaliacaoList;
  }
}
