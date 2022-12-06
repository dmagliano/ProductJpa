package br.dev.magliano.productjpa.repository;

import br.dev.magliano.productjpa.entity.AnuncioProduto;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnuncioRepository extends JpaRepository<AnuncioProduto, Long> {


  List<AnuncioProduto> findAllByProdutoIdAndDataComecoBetweenOrDataFimBetween(Long produto_id,
      LocalDateTime dataComeco, LocalDateTime dataComeco2, LocalDateTime dataFim,
      LocalDateTime dataFim2);

}
