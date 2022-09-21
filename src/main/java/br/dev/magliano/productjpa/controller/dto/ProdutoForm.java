package br.dev.magliano.productjpa.controller.dto;

import br.dev.magliano.productjpa.entity.Produto;
import br.dev.magliano.productjpa.entity.Usuario;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Getter
@NoArgsConstructor
public class ProdutoForm {

    @NotNull
    private String codigoBarras;

    private String nome;

    private String descricao;

    private BigDecimal preco;

    public ProdutoForm(String codigoBarras, String nome, String descricao, BigDecimal preco) {
        this.codigoBarras = codigoBarras;
        this.nome = nome;
        this.descricao = descricao;
        this.preco = preco;
    }

    public Produto toEntity(Usuario usuario) {
        return new Produto(this.codigoBarras, this.nome, this.descricao, this.preco, usuario);
    }
}
