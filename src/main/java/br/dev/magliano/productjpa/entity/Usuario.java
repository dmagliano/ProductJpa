package br.dev.magliano.productjpa.entity;

import br.dev.magliano.productjpa.controller.dto.UsuarioDto;
import br.dev.magliano.productjpa.security.PlainPassword;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Long id;

    private String username;

    private String nome;

    private String email;

    private String senha;

    private LocalDateTime dataCriacao = LocalDateTime.now();

    public Usuario() {
    }

    public Usuario(String username, String nome, String email, PlainPassword plainPassword) {
        this.username = username;
        this.nome = nome;
        this.email = email;
        this.senha = plainPassword.encode();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UsuarioDto toDto() {
        return new UsuarioDto(
                this.id,
                this.username,
                this.nome,
                this.email
        );
    }
}
