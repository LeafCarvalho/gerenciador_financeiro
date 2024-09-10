package dev.leaf_carvalho.gerenciador_financeiro.service;

import dev.leaf_carvalho.gerenciador_financeiro.dto.EntradasDTO;
import dev.leaf_carvalho.gerenciador_financeiro.exception.ResourceNotFoundException;
import dev.leaf_carvalho.gerenciador_financeiro.model.Entradas;
import dev.leaf_carvalho.gerenciador_financeiro.model.Usuarios;
import dev.leaf_carvalho.gerenciador_financeiro.model.Tipo_Recorrencia;
import dev.leaf_carvalho.gerenciador_financeiro.repository.EntradasRepository;
import dev.leaf_carvalho.gerenciador_financeiro.repository.UsuariosRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EntradasService {

    private final EntradasRepository entradasRepository;
    private final UsuariosRepository usuariosRepository;
    private final RecorrenciaService recorrenciaService;

    public EntradasService(EntradasRepository entradasRepository, UsuariosRepository usuariosRepository, RecorrenciaService recorrenciaService) {
        this.entradasRepository = entradasRepository;
        this.usuariosRepository = usuariosRepository;
        this.recorrenciaService = recorrenciaService;
    }

    public List<EntradasDTO> getAllEntradasByUsuarioId(Long usuarioId) {
        return entradasRepository.findByUsuarioId(usuarioId)
                .stream()
                .map(this::convertToDto)
                .toList();
    }

    public EntradasDTO getEntrada(Long id) {
        Entradas entrada = entradasRepository.findById(id)
        		.orElseThrow(() -> new ResourceNotFoundException("Entrada de ID " + id + " não encontrada."));
        return convertToDto(entrada);
    }

    @Transactional
    public EntradasDTO saveEntrada(EntradasDTO entradaDTO, Long usuarioId) {
        Usuarios usuario = usuariosRepository.findById(usuarioId)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        Tipo_Recorrencia recorrencia = recorrenciaService.getRecorrenciaById(entradaDTO.getIdRecorrencia());

        Entradas entrada = new Entradas();
        entrada.setUsuario(usuario);
        entrada.setSalario(entradaDTO.getSalario());
        entrada.setNomeEntrada(entradaDTO.getNomeEntrada());
        entrada.setTipoEntrada(entradaDTO.getTipoEntrada());
        entrada.setTipoRecorrencia(recorrencia);
        entrada.setValorEntrada(entradaDTO.getValorEntrada());
        entrada.setDataEntrada(entradaDTO.getDataEntrada());
        entrada.setReciboEntrada(entradaDTO.getReciboEntrada());
        
        usuario.getEntradas().add(entrada);
        entrada.setUsuario(usuario);

        Entradas savedEntrada = entradasRepository.save(entrada);
        return convertToDto(savedEntrada);
    }

    private EntradasDTO convertToDto(Entradas entrada) {
        return new EntradasDTO(
                entrada.getUsuario().getId(),
                entrada.getSalario(),
                entrada.getNomeEntrada(),
                entrada.getTipoEntrada(),
                entrada.getValorEntrada(),
                entrada.getDataEntrada(),
                entrada.getReciboEntrada(),
                entrada.getTipoRecorrencia().getIdRecorrencia(),
                entrada.getTipoRecorrencia().getTipoRecorrencia()
        );
    }

    @Transactional
    public void deleteEntrada(Long id) {
        entradasRepository.deleteById(id);
    }
}
