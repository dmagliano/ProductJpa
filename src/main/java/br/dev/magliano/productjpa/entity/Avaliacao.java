package br.dev.magliano.productjpa.entity;

import br.dev.magliano.productjpa.controller.dto.AvaliacaoDTO;
import br.dev.magliano.productjpa.controller.dto.AvaliacaoProdutoDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.lang.NonNull;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Getter @Setter @NoArgsConstructor
public class Avaliacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    private String avaliacao;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "PRODUTO_ID", nullable = false)
    private Produto produto;

    @ManyToOne
    @NotNull
    private Usuario usuario;

    private LocalDateTime dataCriacao = LocalDateTime.now();

    public Avaliacao(@NonNull String avaliacao, @NonNull Produto produto, @NonNull Usuario usuario) {
        this.avaliacao = avaliacao;
        this.produto = produto;
        this.usuario = usuario;
    }

    public AvaliacaoProdutoDTO toAvaliacaoProdutoDTO(){
        return new AvaliacaoProdutoDTO(
            this.produto.getId(),
            this.produto.getNome(),
            this.id,
            this.avaliacao
        );
    }

    public AvaliacaoDTO toAvaliacaoDTO(){
        return new AvaliacaoDTO(
                this.id,
                this.avaliacao,
                this.usuario.getId(),
                this.usuario.getUsername(),
                this.dataCriacao
        );
    }
}
