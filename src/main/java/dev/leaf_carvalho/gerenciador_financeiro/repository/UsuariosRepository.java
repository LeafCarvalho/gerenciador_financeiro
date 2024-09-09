package dev.leaf_carvalho.gerenciador_financeiro.repository;

import dev.leaf_carvalho.gerenciador_financeiro.model.Usuarios;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UsuariosRepository extends JpaRepository<Usuarios, Long> {

    @EntityGraph(attributePaths = {"entradas"})
    List<Usuarios> findAll();

    @EntityGraph(attributePaths = {"entradas"})
    Optional<Usuarios> findById(Long id);
    
    Optional<Usuarios> findByEmail(String email);
}
