package br.dev.magliano.productjpa.controller.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class AvaliacaoProdutoDTO {

    private Long idProduto;

    private String nomeProduto;

    private Long idAvaliacao;

    private String avaliacao;

    public AvaliacaoProdutoDTO( Long idProduto, String nomeProduto, Long idAvaliacao, String avaliacao) {
        this.idProduto = idProduto;
        this.nomeProduto = nomeProduto;
        this.idAvaliacao = idAvaliacao;
        this.avaliacao = avaliacao;
    }
}
