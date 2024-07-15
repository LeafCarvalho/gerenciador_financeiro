package dev.leaf_carvalho.gerenciador_financeiro.service;

import dev.leaf_carvalho.gerenciador_financeiro.model.Saidas;
import dev.leaf_carvalho.gerenciador_financeiro.repository.SaidasRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SaidasService {

    private final SaidasRepository saidasRepository;

    public SaidasService(SaidasRepository saidasRepository) {
        this.saidasRepository = saidasRepository;
    }

    public List<Saidas> getAllSaidas() {
        return saidasRepository.findAll();
    }
    
}