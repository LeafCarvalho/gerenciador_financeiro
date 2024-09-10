package dev.leaf_carvalho.gerenciador_financeiro.controller;

import dev.leaf_carvalho.gerenciador_financeiro.dto.EntradasDTO;
import dev.leaf_carvalho.gerenciador_financeiro.model.Usuarios;
import dev.leaf_carvalho.gerenciador_financeiro.service.EntradasService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/entradas")
public class EntradasController {

    private final EntradasService entradasService;

    public EntradasController(EntradasService entradasService) {
        this.entradasService = entradasService;
    }

    @GetMapping
    public List<EntradasDTO> getAllEntradas(Authentication authentication) {
        Usuarios usuario = (Usuarios) authentication.getPrincipal();
        Long usuarioId = usuario.getId();
        return entradasService.getAllEntradasByUsuarioId(usuarioId);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntradasDTO> getEntrada(@PathVariable Long id) {
        EntradasDTO entrada = entradasService.getEntrada(id);
        return ResponseEntity.ok(entrada);
    }

    @PostMapping
    public ResponseEntity<EntradasDTO> saveEntrada(@RequestBody EntradasDTO entradaDTO, Authentication authentication) {
        Usuarios usuario = (Usuarios) authentication.getPrincipal();
        Long usuarioId = usuario.getId();
        EntradasDTO newEntrada = entradasService.saveEntrada(entradaDTO, usuarioId);
        return ResponseEntity.status(201).body(newEntrada);
    }

    @DeleteMapping("/{id}")
    public void deleteEntrada(@PathVariable Long id) {
        entradasService.deleteEntrada(id);
    }
}
