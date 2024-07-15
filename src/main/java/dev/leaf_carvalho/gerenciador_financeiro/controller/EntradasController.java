package dev.leaf_carvalho.gerenciador_financeiro.controller;

import dev.leaf_carvalho.gerenciador_financeiro.model.Entradas;
import dev.leaf_carvalho.gerenciador_financeiro.service.EntradasService;
import org.springframework.web.bind.annotation.GetMapping;
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

}