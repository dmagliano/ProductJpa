package br.dev.magliano.productjpa.controller.dto;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AnuncioForm {

  private Long idProduto;

  private LocalDateTime dataComeco;

  private LocalDateTime dataFim;

}
