package dev.leaf_carvalho.gerenciador_financeiro.controller;

import dev.leaf_carvalho.gerenciador_financeiro.model.Usuarios;
import dev.leaf_carvalho.gerenciador_financeiro.service.UsuariosService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/usuarios")
public class UsuariosController {

    private final UsuariosService usuariosService;

    public UsuariosController(UsuariosService usuariosService) {
        this.usuariosService = usuariosService;
    }

    @GetMapping("/lista")
    public ResponseEntity<Map<String, Object>> getAllUsuarios() {
        List<Usuarios> usuarios = usuariosService.getAllUsuarios();
        Map<String, Object> response = new HashMap<>();
        response.put("data", usuarios);
        return ResponseEntity.ok(response);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Usuarios> getUsuarioPorId(@PathVariable Long id) {
        Usuarios usuario = usuariosService.getUsuarioPorId(id);
        return ResponseEntity.ok(usuario);
    }
}
