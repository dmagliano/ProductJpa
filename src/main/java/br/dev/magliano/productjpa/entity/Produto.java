package br.dev.magliano.productjpa.entity;

import br.dev.magliano.productjpa.controller.dto.AvaliacaoOutputDTO;
import br.dev.magliano.productjpa.controller.dto.ProdutoDetalhesDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Getter @Setter @NoArgsConstructor
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String codigoBarras;

    private String nome;

    private String descricao;

    private BigDecimal preco;

    @ManyToOne
    private Usuario vendedor;

    @OneToMany(
            mappedBy = "produto",
            cascade = CascadeType.ALL)
    private List<Avaliacao> avaliacaoList;


    public void adicionaAvaliacao(Avaliacao avaliacao){
        this.avaliacaoList.add(avaliacao);
    }


    public Produto(String codigoBarras, String nome, String descricao, BigDecimal preco, Usuario vendedor) {
        this.codigoBarras = codigoBarras;
        this.nome = nome;
        this.descricao = descricao;
        this.preco = preco;
        this.vendedor = vendedor;
    }

    public ProdutoDetalhesDTO toDetalhesDTO() {

        List<AvaliacaoOutputDTO> avaliacaoOutput = new ArrayList<>();

        if (this.avaliacaoList != null){
            avaliacaoOutput = this.avaliacaoList.stream().map(Avaliacao::toAvaliacaoDTO).collect(Collectors.toList());
        }

        return new ProdutoDetalhesDTO(
                this.id,
                this.codigoBarras,
                this.nome,
                this.descricao,
                this.preco,
                this.vendedor.getUsername(),
                avaliacaoOutput
        );
    }
}
