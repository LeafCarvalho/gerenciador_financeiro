package dev.leaf_carvalho.gerenciador_financeiro.controller;

import dev.leaf_carvalho.gerenciador_financeiro.model.Entradas;
import dev.leaf_carvalho.gerenciador_financeiro.service.EntradasService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/entradas")
public class EntradasController {

    private final EntradasService entradasService;

    public EntradasController(EntradasService entradasService) {
        this.entradasService = entradasService;
    }

    @GetMapping
    public List<Entradas> getAllEntradas() {
        return entradasService.getAllEntradas();
    }
    
    @GetMapping("/{idEntrada}")
	public void getEntrada(@PathVariable("idEntrada") Long id) {
		entradasService.getEntrada(id);
	}
	
    @PostMapping
	public void saveEntrada(@RequestBody Entradas entrada) {
		entradasService.saveEntrada(entrada);
	}
	
    @DeleteMapping("/{idEntrada}")
	public void deleteEntrada(@PathVariable("idEntrada") Long id) {
		entradasService.deleteEntrada(id);
	}

}