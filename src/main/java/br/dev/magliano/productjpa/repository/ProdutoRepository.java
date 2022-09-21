package br.dev.magliano.productjpa.repository;

import br.dev.magliano.productjpa.entity.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {
}
