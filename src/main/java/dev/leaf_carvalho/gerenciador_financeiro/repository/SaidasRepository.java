package dev.leaf_carvalho.gerenciador_financeiro.repository;

import dev.leaf_carvalho.gerenciador_financeiro.model.Saidas;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface SaidasRepository extends JpaRepository<Saidas, Long> {
    List<Saidas> findByUsuarioId(Long usuarioId);
}
