package dev.leaf_carvalho.gerenciador_financeiro.service;

import dev.leaf_carvalho.gerenciador_financeiro.dto.InvestimentosDTO;
import dev.leaf_carvalho.gerenciador_financeiro.model.Investimentos;
import dev.leaf_carvalho.gerenciador_financeiro.model.Tipo_Recorrencia;
import dev.leaf_carvalho.gerenciador_financeiro.model.Usuarios;
import dev.leaf_carvalho.gerenciador_financeiro.repository.InvestimentosRepository;
import dev.leaf_carvalho.gerenciador_financeiro.repository.UsuariosRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class InvestimentosService {

    private final InvestimentosRepository investimentosRepository;
    private final UsuariosRepository usuariosRepository;
    private final RecorrenciaService recorrenciaService;

    public InvestimentosService(InvestimentosRepository investimentosRepository, UsuariosRepository usuariosRepository, RecorrenciaService recorrenciaService) {
        this.investimentosRepository = investimentosRepository;
        this.usuariosRepository = usuariosRepository;
        this.recorrenciaService = recorrenciaService;
    }

    public List<InvestimentosDTO> getAllInvestimentosByUsuarioId(Long usuarioId) {
        return investimentosRepository.findByUsuarioId(usuarioId)
                .stream()
                .map(this::convertToDto)
                .toList();
    }

    public InvestimentosDTO getInvestimentoById(Long id) {
        Investimentos investimento = investimentosRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Investimento não encontrado"));
        return convertToDto(investimento);
    }

    @Transactional
    public InvestimentosDTO saveInvestimento(InvestimentosDTO investimentosDTO, Long usuarioId) {
        Usuarios usuario = usuariosRepository.findById(usuarioId)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        Tipo_Recorrencia recorrencia = recorrenciaService.getRecorrenciaById(investimentosDTO.getIdRecorrencia());

        if (investimentosDTO.getCategoria() == null || investimentosDTO.getCategoria().isEmpty()) {
            throw new RuntimeException("Categoria não pode ser nula");
        }

        Investimentos investimento = new Investimentos();
        investimento.setUsuario(usuario);
        investimento.setNomeInvestimento(investimentosDTO.getNomeInvestimento());
        investimento.setTipoInvestimento(investimentosDTO.getTipoInvestimento());
        investimento.setValorInvestimento(investimentosDTO.getValorInvestimento());
        investimento.setDataInvestimentoInicial(investimentosDTO.getDataInvestimentoInicial());
        investimento.setDataInvestimentoFinal(investimentosDTO.getDataInvestimentoFinal());
        investimento.setReciboInvestimento(investimentosDTO.getReciboInvestimento());
        investimento.setTipoRecorrencia(recorrencia);
        investimento.setCategoria(investimentosDTO.getCategoria());

        Investimentos savedInvestimento = investimentosRepository.save(investimento);
        return convertToDto(savedInvestimento);
    }


    @Transactional
    public void deleteById(Long id) {
        investimentosRepository.deleteById(id);
    }

    private InvestimentosDTO convertToDto(Investimentos investimento) {
        return new InvestimentosDTO(
                investimento.getUsuario().getId(),
                investimento.getNomeInvestimento(),
                investimento.getTipoInvestimento(),
                investimento.getValorInvestimento(),
                investimento.getCategoria(),
                investimento.getDataInvestimentoInicial(),
                investimento.getDataInvestimentoFinal(),
                investimento.getReciboInvestimento(),
                investimento.getTipoRecorrencia().getIdRecorrencia(),
                investimento.getTipoRecorrencia().getTipoRecorrencia()
        );
    }
}
