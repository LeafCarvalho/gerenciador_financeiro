package dev.leaf_carvalho.gerenciador_financeiro.service;

import dev.leaf_carvalho.gerenciador_financeiro.dto.InvestimentosDTO;
import dev.leaf_carvalho.gerenciador_financeiro.model.Investimentos;
import dev.leaf_carvalho.gerenciador_financeiro.model.Usuarios;
import dev.leaf_carvalho.gerenciador_financeiro.repository.InvestimentosRepository;
import dev.leaf_carvalho.gerenciador_financeiro.repository.UsuariosRepository;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Service
public class InvestimentosService {

    private final InvestimentosRepository investimentosRepository;
    private final UsuariosRepository usuariosRepository;

    public InvestimentosService(InvestimentosRepository investimentosRepository, UsuariosRepository usuariosRepository) {
        this.investimentosRepository = investimentosRepository;
        this.usuariosRepository = usuariosRepository;
    }

    public List<Investimentos> getAllInvestimentos() {
        return investimentosRepository.findAll();
    }
    
    public Investimentos getInvestimentosById(Long id) {
    	return investimentosRepository.findById(id).orElseThrow();
    }

	public Investimentos saveInvestimentos(InvestimentosDTO investimentosDTO) {
	
	    Usuarios usuario = usuariosRepository.findById(investimentosDTO.getUsuario_id())
	        .orElseThrow(() -> new RuntimeException("User not found"));
	
	    Investimentos investimento = new Investimentos();
	
	    investimento.setUsuario(usuario);
	    investimento.setNomeInvestimento(investimentosDTO.getNomeInvestimento());
	    investimento.setTipoInvestimento(investimentosDTO.getTipoInvestimento());
	    investimento.setRecorrenciaInvestimento(investimentosDTO.getRecorrenciaInvestimento());
	    investimento.setValorInvestimento(BigDecimal.valueOf(investimentosDTO.getValorInvestimento()));
	    investimento.setDataInvestimentoInicial(investimentosDTO.getDataInvestimentoInicial());
	    investimento.setDataInvestimentoFinal(LocalDate.parse(investimentosDTO.getDataInvestimentoFinal()));
	    investimento.setReciboInvestimento(investimentosDTO.getReciboInvestimento());
	
	    return investimentosRepository.save(investimento);
	}

	
	public void deleteInvestimentos(Long id) {
		investimentosRepository.deleteById(id);
	}

}