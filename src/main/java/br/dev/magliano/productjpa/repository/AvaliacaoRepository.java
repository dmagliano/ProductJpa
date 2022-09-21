package br.dev.magliano.productjpa.repository;

import br.dev.magliano.productjpa.entity.Avaliacao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AvaliacaoRepository extends JpaRepository<Avaliacao, Long> {
}
