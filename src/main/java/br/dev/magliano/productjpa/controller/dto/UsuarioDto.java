package br.dev.magliano.productjpa.controller.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter @NoArgsConstructor
public class UsuarioDto {

    private Long id;

    private String username;

    private String nome;

    private String email;

    public UsuarioDto(Long id, String username, String nome, String email) {
        this.id = id;
        this.username = username;
        this.nome = nome;
        this.email = email;
    }
}
