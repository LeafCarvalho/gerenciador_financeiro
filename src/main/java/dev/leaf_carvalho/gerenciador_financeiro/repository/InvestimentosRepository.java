package dev.leaf_carvalho.gerenciador_financeiro.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.leaf_carvalho.gerenciador_financeiro.model.Investimentos;

public interface InvestimentosRepository extends JpaRepository<Investimentos, Long> {

    List<Investimentos> findByUsuarioId(Long usuarioId);
}
