package br.dev.magliano.productjpa.repository;

import br.dev.magliano.productjpa.entity.Produto;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {

  List<Produto> findProdutoByVendedorId(Integer id);

  @Query(
      value = "SELECT * FROM PRODUTO p WHERE p.ID NOT IN (SELECT PRODUTO_ID FROM ANUNCIO_PRODUTO ad WHERE ad.DATA_COMECO <= ?1 and ad.DATA_FIM >= ?2)",
      nativeQuery = true
  )
  List<Produto> findProdutoByDate(LocalDateTime dataFim, LocalDateTime dataComeco);


}
