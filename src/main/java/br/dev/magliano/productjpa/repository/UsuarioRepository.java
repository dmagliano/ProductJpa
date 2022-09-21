package br.dev.magliano.productjpa.repository;

import br.dev.magliano.productjpa.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}
