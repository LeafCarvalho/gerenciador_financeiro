package dev.leaf_carvalho.gerenciador_financeiro.service;

import dev.leaf_carvalho.gerenciador_financeiro.model.Entradas;
import dev.leaf_carvalho.gerenciador_financeiro.repository.EntradasRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EntradasService {

    private final EntradasRepository entradasRepository;

    public EntradasService(EntradasRepository entradasRepository) {
        this.entradasRepository = entradasRepository;
    }

    public List<Entradas> getAllEntradas() {
        return entradasRepository.findAll();
    }

}