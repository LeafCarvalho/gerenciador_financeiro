package dev.leaf_carvalho.gerenciador_financeiro.controller;

import dev.leaf_carvalho.gerenciador_financeiro.dto.InvestimentosDTO;
import dev.leaf_carvalho.gerenciador_financeiro.model.Usuarios;
import dev.leaf_carvalho.gerenciador_financeiro.service.InvestimentosService;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/investimentos")
public class InvestimentosController {

    private final InvestimentosService investimentosService;

    public InvestimentosController(InvestimentosService investimentosService) {
        this.investimentosService = investimentosService;
    }

    @GetMapping
    public List<InvestimentosDTO> getAllInvestimentos(Authentication authentication) {
        Usuarios usuario = (Usuarios) authentication.getPrincipal();
        Long usuarioId = usuario.getId();
        return investimentosService.getAllInvestimentosByUsuarioId(usuarioId);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<InvestimentosDTO> getInvestimentoById(@PathVariable Long id) {
        InvestimentosDTO investimento = investimentosService.getInvestimentoById(id);
        return ResponseEntity.ok(investimento);
    }

    @PostMapping
    public ResponseEntity<InvestimentosDTO> saveInvestimento(@RequestBody InvestimentosDTO investimentosDTO, Authentication authentication) {
        Long usuarioId = ((Usuarios) authentication.getPrincipal()).getId();
        InvestimentosDTO newInvestimento = investimentosService.saveInvestimento(investimentosDTO, usuarioId);
        return ResponseEntity.status(201).body(newInvestimento);
    }
    
    @DeleteMapping("/{id}")
    public void deleteInvestimento(@PathVariable Long id) {
        investimentosService.deleteById(id);
    }

}
