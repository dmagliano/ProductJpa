package br.dev.magliano.productjpa.controller.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Getter @NoArgsConstructor
public class ProdutoDetalhesDTO {

    private Long id;

    private String codigoBarras;

    private String nome;

    private String descricao;

    private BigDecimal preco;

    private String usernameVendedor;

    private List<AvaliacaoOutputDTO> avaliacaoList;

    public ProdutoDetalhesDTO(Long id, String codigoBarras, String nome, String descricao,
                              BigDecimal preco, String usernameVendedor, List<AvaliacaoOutputDTO> avaliacaoList) {
        this.id = id;
        this.codigoBarras = codigoBarras;
        this.nome = nome;
        this.descricao = descricao;
        this.preco = preco;
        this.usernameVendedor = usernameVendedor;
        this.avaliacaoList = avaliacaoList;
    }
}
