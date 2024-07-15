package dev.leaf_carvalho.gerenciador_financeiro.controller;

import dev.leaf_carvalho.gerenciador_financeiro.model.Saidas;
import dev.leaf_carvalho.gerenciador_financeiro.service.SaidasService;
import org.springframework.web.bind.annotation.GetMapping;
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

}