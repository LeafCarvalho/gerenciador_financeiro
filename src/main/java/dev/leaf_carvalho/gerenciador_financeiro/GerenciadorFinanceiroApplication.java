package dev.leaf_carvalho.gerenciador_financeiro;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Gerenciador Financeiro API", version = "1", description = "API para gerenciar finanças pessoais, permitindo aos usuários registrar entradas, saídas e investimentos, além de definir recorrências para cada transação. Cada usuário tem sua própria conta para fazer login e gerenciar suas finanças."))
public class GerenciadorFinanceiroApplication {

	public static void main(String[] args) {
		SpringApplication.run(GerenciadorFinanceiroApplication.class, args);
	}

}
