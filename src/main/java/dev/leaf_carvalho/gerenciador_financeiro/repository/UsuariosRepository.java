package dev.leaf_carvalho.gerenciador_financeiro.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import dev.leaf_carvalho.gerenciador_financeiro.model.Usuarios;

public interface UsuariosRepository extends JpaRepository<Usuarios, Long> {
	Optional<Usuarios> findByEmail(String email);
}
