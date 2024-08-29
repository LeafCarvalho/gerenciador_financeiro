package dev.leaf_carvalho.gerenciador_financeiro.service;

import dev.leaf_carvalho.gerenciador_financeiro.dto.EntradasDTO;
import dev.leaf_carvalho.gerenciador_financeiro.model.Entradas;
import dev.leaf_carvalho.gerenciador_financeiro.model.Usuarios;
import dev.leaf_carvalho.gerenciador_financeiro.repository.EntradasRepository;
import dev.leaf_carvalho.gerenciador_financeiro.repository.UsuariosRepository;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EntradasService {

    private final EntradasRepository entradasRepository;
    private final UsuariosRepository usuariosRepository;

    public EntradasService(EntradasRepository entradasRepository, UsuariosRepository usuariosRepository) {
        this.entradasRepository = entradasRepository;
        this.usuariosRepository = usuariosRepository;
    }

    public List<Entradas> getAllEntradas() {
        return entradasRepository.findAll();
    }
    
    public void getEntrada(Long id) {
    	entradasRepository.findById(id);
    }

	public Entradas saveEntrada(EntradasDTO entradaDTO) {
	
	    Usuarios usuario = usuariosRepository.findById(entradaDTO.getUsuario_id())
	        .orElseThrow(() -> new RuntimeException("User not found"));
	
	    Entradas entrada = new Entradas();
	
	    entrada.setUsuario(usuario);
	    entrada.setSalario(entradaDTO.getSalario());
	    entrada.setNomeEntrada(entradaDTO.getNomeEntrada());
	    entrada.setTipoEntrada(entradaDTO.getTipoEntrada());
	    entrada.setRecorrenciaEntrada(entradaDTO.getRecorrenciaEntrada());
	    entrada.setValorEntrada(entradaDTO.getValorEntrada());
	    entrada.setDataEntrada(entradaDTO.getDataEntrada());
	    entrada.setReciboEntrada(entradaDTO.getReciboEntrada());
	
	    return entradasRepository.save(entrada);
	}

	public void deleteEntrada(Long id) {
		entradasRepository.deleteById(id);
	}

}