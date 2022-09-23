package br.dev.magliano.productjpa.repository;

import br.dev.magliano.productjpa.entity.Avaliacao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AvaliacaoRepository extends JpaRepository<Avaliacao, Long> {

    List<Avaliacao> findAvaliacaoByProdutoId(Long idProduto);
}
