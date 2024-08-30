package dev.leaf_carvalho.gerenciador_financeiro.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import dev.leaf_carvalho.gerenciador_financeiro.model.Usuarios;
import dev.leaf_carvalho.gerenciador_financeiro.service.UsuariosService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/usuarios")
@Tag(name = "Usuarios", description = "Operações relacionadas aos usuários")
public class UsuariosController {
    private final UsuariosService usuariosService;

    public UsuariosController(UsuariosService usuariosService) {
        this.usuariosService = usuariosService;
    }

	@GetMapping("/lista")
	@Operation(summary = "Obter todos os usuários", method = "GET", description = "Retorna todos os usuários se o token for do administrador do sistema")
	@ApiResponses(value = { 
	  @ApiResponse(responseCode = "200", description = "Retornou com sucesso a lista de usuários"),
	  @ApiResponse(responseCode = "403", description = "Não autorizado, token inválido ou não é do administrador")
	})
	public ResponseEntity<Map<String, Object>> getAllSaidas() {
	    List<Usuarios> usuarios = usuariosService.getAllSaidas();
	    Map<String, Object> response = new HashMap<String, Object>();
	    response.put("data", usuarios);
	    return ResponseEntity.ok(response);
	}

}
