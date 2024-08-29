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

@RestController
@RequestMapping("/usuarios")
public class UsuariosController {
    private final UsuariosService usuariosService;

    public UsuariosController(UsuariosService usuariosService) {
        this.usuariosService = usuariosService;
    }

	@GetMapping("/lista")
	public ResponseEntity<Map<String, Object>> getAllSaidas() {
	    List<Usuarios> usuarios = usuariosService.getAllSaidas();
	    Map<String, Object> response = new HashMap<String, Object>();
	    response.put("data", usuarios);
	    return ResponseEntity.ok(response);
	}

}
