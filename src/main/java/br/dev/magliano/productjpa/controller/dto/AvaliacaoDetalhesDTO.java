package br.dev.magliano.productjpa.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class AvaliacaoDetalhesDTO {

    private Long id;

    private String avaliacao;

    private String usuario;

    private LocalDateTime dataCriacao;

}
