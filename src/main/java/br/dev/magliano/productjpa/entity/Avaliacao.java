package br.dev.magliano.productjpa.entity;

import br.dev.magliano.productjpa.controller.dto.AvaliacaoOutputDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.lang.NonNull;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Getter @Setter @NoArgsConstructor
public class Avaliacao {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NonNull
    private String avaliacao;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "PRODUTO_ID", nullable = false)
    private Produto produto;

    @ManyToOne
    @NotNull
    private Usuario usuario;

    public Avaliacao(@NonNull String avaliacao, @NonNull Produto produto, @NonNull Usuario usuario) {
        this.avaliacao = avaliacao;
        this.produto = produto;
        this.usuario = usuario;
    }

    public AvaliacaoOutputDTO toAvaliacaoDTO(){
        return new AvaliacaoOutputDTO(
                this.avaliacao,
                this.usuario.getUsername()
        );
    }
}
