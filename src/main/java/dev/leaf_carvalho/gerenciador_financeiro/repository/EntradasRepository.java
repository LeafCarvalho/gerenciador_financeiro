package dev.leaf_carvalho.gerenciador_financeiro.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import dev.leaf_carvalho.gerenciador_financeiro.model.Entradas;
import java.util.List;

public interface EntradasRepository extends JpaRepository<Entradas, Long> {

    List<Entradas> findByUsuarioId(Long usuarioId);
}
