package br.dev.magliano.productjpa.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class AvaliacaoDTO {

    private Long idAvaliacao;

    private String avaliacao;

    private Long idUsuario;

    private String usuario;

    private LocalDateTime dataCriacao;

}
