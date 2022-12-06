package br.dev.magliano.productjpa.controller.dto;

import br.dev.magliano.productjpa.entity.Produto;
import java.time.LocalDateTime;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AnuncioDto {

  private Long idAnuncio;

  private String nomeProduto;

  private Long idProduto;

  @DateTimeFormat(iso = ISO.DATE_TIME)
  private LocalDateTime dataComeco;

  @DateTimeFormat(iso = ISO.DATE_TIME)
  private LocalDateTime dataFim;

}
