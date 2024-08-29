package dev.leaf_carvalho.gerenciador_financeiro.service;

import dev.leaf_carvalho.gerenciador_financeiro.dto.SaidasDTO;
import dev.leaf_carvalho.gerenciador_financeiro.model.Saidas;
import dev.leaf_carvalho.gerenciador_financeiro.model.Usuarios;
import dev.leaf_carvalho.gerenciador_financeiro.repository.SaidasRepository;
import dev.leaf_carvalho.gerenciador_financeiro.repository.UsuariosRepository;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SaidasService {

    private final SaidasRepository saidasRepository;
    private final UsuariosRepository usuariosRepository;

    public SaidasService(SaidasRepository saidasRepository, UsuariosRepository usuariosRepository) {
        this.saidasRepository = saidasRepository;
        this.usuariosRepository = usuariosRepository;
    }

    public List<Saidas> getAllSaidas() {
        return saidasRepository.findAll();
    }
    
    public Saidas getById(Long id) {
    	return saidasRepository.findById(id).orElse(null);
    }
    

	public Saidas saveSaida(SaidasDTO saidaDTO) {
	
	    Usuarios usuario = usuariosRepository.findById(saidaDTO.getUsuario_id())
	        .orElseThrow(() -> new RuntimeException("User not found"));
	
	    Saidas saida = new Saidas();
	
	    saida.setUsuario(usuario);
	    saida.setNomeSaida(saidaDTO.getNomeSaida());
	    saida.setTipoSaida(saidaDTO.getTipoSaida());
	    saida.setRecorrenciaSaida(saidaDTO.getRecorrenciaSaida());
	    saida.setValorSaida(saidaDTO.getValorSaida());
	    saida.setCategoria(saidaDTO.getCategoria());
	    saida.setDataVencimento(saidaDTO.getDataVencimento());
	    saida.setReciboSaida(saidaDTO.getReciboSaida());
	
	    return saidasRepository.save(saida);
	}

	
	public void deleteById(Long id) {
		saidasRepository.deleteById(id);
	}

}