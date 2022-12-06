package br.dev.magliano.productjpa.controller.dto;

import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class AvaliacaoListUsuarioDto {

  private Long idUsuario;

  private String username;

  private List<AvaliacaoProdutoDTO> avaliacaoList;

  public AvaliacaoListUsuarioDto(Long idUsuario, String username,
      List<AvaliacaoProdutoDTO> avaliacaoList) {
    this.idUsuario = idUsuario;
    this.username = username;
    this.avaliacaoList = avaliacaoList;
  }
}
