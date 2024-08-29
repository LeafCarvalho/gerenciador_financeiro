package dev.leaf_carvalho.gerenciador_financeiro.controller;

import dev.leaf_carvalho.gerenciador_financeiro.dto.SaidasDTO;
import dev.leaf_carvalho.gerenciador_financeiro.model.Saidas;
import dev.leaf_carvalho.gerenciador_financeiro.service.SaidasService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/saidas")
public class SaidasController {

    private final SaidasService saidasService;

    public SaidasController(SaidasService saidasService) {
        this.saidasService = saidasService;
    }

    @GetMapping
    public List<Saidas> getAllSaidas() {
        return saidasService.getAllSaidas();
    }
    
    @GetMapping("/{idSaida}")
	public Saidas getById(@PathVariable("idSaida") Long id) {
		return saidasService.getById(id);
	}
    
    @PostMapping
    public ResponseEntity<Saidas> save(@RequestBody SaidasDTO saidaDTO) {
        Saidas newSaida = saidasService.saveSaida(saidaDTO);
        return ResponseEntity.status(201).body(newSaida);
    }
    
    @DeleteMapping("/{idSaida}")
        public void deleteById(@PathVariable("idSaida") Long id) {
            saidasService.deleteById(id);
    }

}