package br.dev.magliano.productjpa.controller.dto;

import br.dev.magliano.productjpa.entity.Usuario;
import br.dev.magliano.productjpa.security.PlainPassword;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter @NoArgsConstructor
public class UsuarioForm {

    private String username;

    private String nome;

    private String email;

    private String senha;

    public UsuarioForm(String username, String nome, String email, String senha) {
        this.username = username;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
    }

    public Usuario toEntity(){
        return new Usuario(
                this.username,
                this.nome,
                this.email,
                new PlainPassword(this.senha)
        );
    }
}
