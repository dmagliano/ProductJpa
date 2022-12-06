package br.dev.magliano.productjpa.entity;

import br.dev.magliano.productjpa.controller.dto.AnuncioDto;
import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class AnuncioProduto {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long idAnuncio;

  @OneToOne
  private Produto produto;

  @DateTimeFormat(iso = ISO.DATE_TIME)
  private LocalDateTime dataComeco;

  @DateTimeFormat(iso = ISO.DATE_TIME)
  private LocalDateTime dataFim;

  public AnuncioProduto(Produto produto, LocalDateTime dataComeco, LocalDateTime dataFim) {
    this.produto = produto;
    this.dataComeco = dataComeco;
    this.dataFim = dataFim;
  }

  public AnuncioDto toDto(){
    return new AnuncioDto(
        this.idAnuncio,
        this.produto.getNome(),
        this.produto.getId(),
        this.dataComeco,
        this.dataFim
    );
  }
}
