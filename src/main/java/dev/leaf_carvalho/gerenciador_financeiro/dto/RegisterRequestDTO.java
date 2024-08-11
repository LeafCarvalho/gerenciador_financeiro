package dev.leaf_carvalho.gerenciador_financeiro.dto;

import dev.leaf_carvalho.gerenciador_financeiro.model.Roles;

public record RegisterRequestDTO (String username, String email, String senha, Roles role) {
}
