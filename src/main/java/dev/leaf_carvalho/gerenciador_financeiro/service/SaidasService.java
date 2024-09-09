package dev.leaf_carvalho.gerenciador_financeiro.service;

import dev.leaf_carvalho.gerenciador_financeiro.dto.SaidasDTO;
import dev.leaf_carvalho.gerenciador_financeiro.model.Saidas;
import dev.leaf_carvalho.gerenciador_financeiro.model.Tipo_Recorrencia;
import dev.leaf_carvalho.gerenciador_financeiro.model.Usuarios;
import dev.leaf_carvalho.gerenciador_financeiro.repository.SaidasRepository;
import dev.leaf_carvalho.gerenciador_financeiro.repository.UsuariosRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SaidasService {

    private final SaidasRepository saidasRepository;
    private final UsuariosRepository usuariosRepository;
    private final RecorrenciaService recorrenciaService;


    public SaidasService(SaidasRepository saidasRepository, UsuariosRepository usuariosRepository, RecorrenciaService recorrenciaService) {
        this.saidasRepository = saidasRepository;
        this.usuariosRepository = usuariosRepository;
        this.recorrenciaService = recorrenciaService;
    }

    public List<SaidasDTO> getAllSaidasByUsuarioId(Long usuarioId) {
        return saidasRepository.findByUsuarioId(usuarioId)
                .stream()
                .map(this::convertToDto)
                .toList();
    }

    public SaidasDTO getById(Long id) {
        Saidas saida = saidasRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Saída não encontrada"));
        return convertToDto(saida);
    }

    @Transactional
    public SaidasDTO saveSaida(SaidasDTO saidaDTO, Long usuarioId) {
        Usuarios usuario = usuariosRepository.findById(usuarioId)
            .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        Tipo_Recorrencia recorrencia = recorrenciaService.getRecorrenciaById(saidaDTO.getIdRecorrencia());

        Saidas saida = new Saidas();
        saida.setUsuario(usuario);
        saida.setNomeSaida(saidaDTO.getNomeSaida());
        saida.setTipoSaida(saidaDTO.getTipoSaida());
        saida.setTipoRecorrencia(recorrencia);
        saida.setValorSaida(saidaDTO.getValorSaida());
        saida.setCategoria(saidaDTO.getCategoria());
        saida.setDataVencimento(saidaDTO.getDataVencimento());
        saida.setReciboSaida(saidaDTO.getReciboSaida());

        Saidas savedSaida = saidasRepository.save(saida);
        return convertToDto(savedSaida);
    }


    @Transactional
    public void deleteById(Long id) {
        saidasRepository.deleteById(id);
    }

    private SaidasDTO convertToDto(Saidas saida) {
        return new SaidasDTO(
            saida.getUsuario().getId(),
            saida.getNomeSaida(),
            saida.getTipoSaida(),
            saida.getValorSaida(),
            saida.getCategoria(),
            saida.getDataVencimento(),
            saida.getReciboSaida(),
            saida.getTipoRecorrencia().getIdRecorrencia(),
            saida.getTipoRecorrencia().getTipoRecorrencia()
        );
    }

}
