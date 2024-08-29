package dev.leaf_carvalho.gerenciador_financeiro.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.leaf_carvalho.gerenciador_financeiro.model.Tipo_Recorrencia;

public interface RecorrenciaRepository extends JpaRepository<Tipo_Recorrencia, Long> {
}