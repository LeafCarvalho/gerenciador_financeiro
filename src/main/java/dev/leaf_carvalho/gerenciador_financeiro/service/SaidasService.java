package dev.leaf_carvalho.gerenciador_financeiro.service;

import dev.leaf_carvalho.gerenciador_financeiro.dto.SaidasDTO;
import dev.leaf_carvalho.gerenciador_financeiro.exception.ResourceNotFoundException;
import dev.leaf_carvalho.gerenciador_financeiro.model.Saidas;
import dev.leaf_carvalho.gerenciador_financeiro.model.Tipo_Recorrencia;
import dev.leaf_carvalho.gerenciador_financeiro.model.Usuarios;
import dev.leaf_carvalho.gerenciador_financeiro.repository.SaidasRepository;
import dev.leaf_carvalho.gerenciador_financeiro.repository.UsuariosRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class SaidasService {

    private final SaidasRepository saidasRepository;
    private final UsuariosRepository usuariosRepository;
    private final RecorrenciaService recorrenciaService;
    private final ArmazenaArquivoService armazenaArquivoService;

    public SaidasService(SaidasRepository saidasRepository,
                         UsuariosRepository usuariosRepository,
                         RecorrenciaService recorrenciaService,
                         ArmazenaArquivoService armazenaArquivoService) {
        this.saidasRepository = saidasRepository;
        this.usuariosRepository = usuariosRepository;
        this.recorrenciaService = recorrenciaService;
        this.armazenaArquivoService = armazenaArquivoService;
    }

    public List<SaidasDTO> getAllSaidasByUsuarioId(Long usuarioId) {
        return saidasRepository.findByUsuarioId(usuarioId)
                .stream()
                .map(this::convertToDto)
                .toList();
    }

    public SaidasDTO getById(Long id) {
        Saidas saida = saidasRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Saída de ID " + id + " não encontrada."));
        return convertToDto(saida);
    }

    @Transactional
    public SaidasDTO saveSaida(SaidasDTO saidaDTO, Long usuarioId, MultipartFile file) {
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

        if (file != null && !file.isEmpty()) {
            String contentType = file.getContentType();
            if (!isValidContentType(contentType)) {
                throw new RuntimeException("Tipo de arquivo não suportado");
            }

            String filePath = armazenaArquivoService.storeFile(file);
            saida.setReciboSaida(filePath);
        } else {
            saida.setReciboSaida(null);
        }

        Saidas savedSaida = saidasRepository.save(saida);
        return convertToDto(savedSaida);
    }

    @Transactional
    public void deleteById(Long id) {
        saidasRepository.deleteById(id);
    }

    private SaidasDTO convertToDto(Saidas saida) {
        return new SaidasDTO(
        		saida.getId(),
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

    private boolean isValidContentType(String contentType) {
        return contentType.equals("application/pdf") ||
               contentType.equals("image/jpeg") ||
               contentType.equals("image/png");
    }
}
