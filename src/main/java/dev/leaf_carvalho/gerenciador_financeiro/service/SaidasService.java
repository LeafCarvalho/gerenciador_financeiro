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
    
    public Saidas getById(Long id) {
    	return saidasRepository.findById(id).orElse(null);
    }
    
	public Saidas save(Saidas saida) {
		return saidasRepository.save(saida);
	}
	
	public void deleteById(Long id) {
		saidasRepository.deleteById(id);
	}

}