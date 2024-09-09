package dev.leaf_carvalho.gerenciador_financeiro.controller;

import dev.leaf_carvalho.gerenciador_financeiro.dto.SaidasDTO;
import dev.leaf_carvalho.gerenciador_financeiro.model.Usuarios;
import dev.leaf_carvalho.gerenciador_financeiro.service.SaidasService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/saidas")
public class SaidasController {

    private final SaidasService saidasService;

    public SaidasController(SaidasService saidasService) {
        this.saidasService = saidasService;
    }

    @GetMapping
    public List<SaidasDTO> getAllSaidas(Authentication authentication) {
        Usuarios usuario = (Usuarios) authentication.getPrincipal();
        Long usuarioId = usuario.getId();
        return saidasService.getAllSaidasByUsuarioId(usuarioId);
    }

    @GetMapping("/{idSaida}")
    public ResponseEntity<SaidasDTO> getById(@PathVariable Long idSaida) {
        SaidasDTO saida = saidasService.getById(idSaida);
        return ResponseEntity.ok(saida);
    }

    @PostMapping
    public ResponseEntity<SaidasDTO> save(@RequestBody SaidasDTO saidaDTO, Authentication authentication) {
        Long usuarioId = ((Usuarios) authentication.getPrincipal()).getId();
        SaidasDTO newSaida = saidasService.saveSaida(saidaDTO, usuarioId);
        return ResponseEntity.status(201).body(newSaida);
    }

    @DeleteMapping("/{idSaida}")
    public void deleteById(@PathVariable Long idSaida) {
        saidasService.deleteById(idSaida);
    }
}
