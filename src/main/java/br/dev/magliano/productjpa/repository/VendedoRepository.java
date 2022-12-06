package br.dev.magliano.productjpa.repository;

import br.dev.magliano.productjpa.entity.Vendedor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VendedoRepository extends JpaRepository<Vendedor, Long> {

}
