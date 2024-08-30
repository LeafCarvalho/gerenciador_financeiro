package dev.leaf_carvalho.gerenciador_financeiro.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.leaf_carvalho.gerenciador_financeiro.model.Tipo_Recorrencia;
import dev.leaf_carvalho.gerenciador_financeiro.service.RecorrenciaService;

@RestController
@RequestMapping("/recorrencias")
public class RecorrenciaController {

	private final RecorrenciaService recorrenciaService;
	
	public RecorrenciaController(RecorrenciaService recorrenciaService) {
		this.recorrenciaService = recorrenciaService;
	}
	
	@GetMapping
	public List<Tipo_Recorrencia> getAllRecorrencias() {
		return recorrenciaService.getAllRecorrencias();
	}
	
	@GetMapping("/{idRecorrencia}")
	public Tipo_Recorrencia getRecorrenciaById(@PathVariable("idRecorrencia") Long id) {
	    return recorrenciaService.getRecorrenciaById(id);
	}

	@PostMapping
	public Tipo_Recorrencia saveRecorrencia(@RequestBody Tipo_Recorrencia recorrencia) {
	    return recorrenciaService.saveRecorrencia(recorrencia);
	}
	
	@DeleteMapping("/{idRecorrencia}")
	public void deleteRecorrencia(@PathVariable("idRecorrencia") Long id) {
	    recorrenciaService.deleteRecorrencia(id);
	}
	
}
