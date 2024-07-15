package dev.leaf_carvalho.gerenciador_financeiro.controller;

import dev.leaf_carvalho.gerenciador_financeiro.model.Usuarios;
import dev.leaf_carvalho.gerenciador_financeiro.service.UsuariosService;

import org.springframework.web.bind.annotation.*;
import java.util.List;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/usuarios")
public class UsuariosController {

    private final UsuariosService usuariosService;

    public UsuariosController(UsuariosService usuariosService) {
        this.usuariosService = usuariosService;
    }

    @GetMapping
    public List<Usuarios> getAllUsuarios() {
        return usuariosService.getAllUsuarios();
    }
    
    @GetMapping("/{id}")
    public Usuarios getUsuario(@PathVariable("id") Long idUsuario) {
        return usuariosService.getUsuario(idUsuario);
    }

    @PostMapping
	public void addUsuarios(@RequestBody Usuarios usuarios) {
		usuariosService.addUsuarios(usuarios);
	}
    
    @DeleteMapping("/{id_usuario}")
	public void deleteUsuario(@PathVariable("id_usuario") Long idUsuario) {
		usuariosService.deleteUsuario(idUsuario);
	}
}