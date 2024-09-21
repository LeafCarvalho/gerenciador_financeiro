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
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class EntradasService {

    private final EntradasRepository entradasRepository;
    private final UsuariosRepository usuariosRepository;
    private final RecorrenciaService recorrenciaService;
    private final ArmazenaArquivoService armazenaArquivoService;

    public EntradasService(EntradasRepository entradasRepository,
                           UsuariosRepository usuariosRepository,
                           RecorrenciaService recorrenciaService,
                           ArmazenaArquivoService armazenaArquivoService) {
        this.entradasRepository = entradasRepository;
        this.usuariosRepository = usuariosRepository;
        this.recorrenciaService = recorrenciaService;
        this.armazenaArquivoService = armazenaArquivoService;
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
    public EntradasDTO saveEntrada(EntradasDTO entradaDTO, Long usuarioId, MultipartFile file) {
        Usuarios usuario = usuariosRepository.findById(usuarioId)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        Tipo_Recorrencia recorrencia = recorrenciaService.getRecorrenciaById(entradaDTO.getIdRecorrencia());

        Entradas entrada = new Entradas();
        entrada.setUsuario(usuario);
        entrada.setNomeEntrada(entradaDTO.getNomeEntrada());
        entrada.setTipoEntrada(entradaDTO.getTipoEntrada());
        entrada.setTipoRecorrencia(recorrencia);
        entrada.setValorEntrada(entradaDTO.getValorEntrada());
        entrada.setDataEntrada(entradaDTO.getDataEntrada());

        if (file != null && !file.isEmpty()) {
            String contentType = file.getContentType();
            if (!isValidContentType(contentType)) {
                throw new RuntimeException("Tipo de arquivo não suportado");
            }

            String filePath = armazenaArquivoService.storeFile(file);
            entrada.setReciboEntrada(filePath);
        } else {
            entrada.setReciboEntrada(null);
        }

        usuario.getEntradas().add(entrada);

        Entradas savedEntrada = entradasRepository.save(entrada);
        return convertToDto(savedEntrada);
    }

    @Transactional
    public void deleteEntrada(Long id) {
        entradasRepository.deleteById(id);
    }

    private EntradasDTO convertToDto(Entradas entrada) {
        return new EntradasDTO(
                entrada.getId(),
                entrada.getUsuario().getId(),
                entrada.getNomeEntrada(),
                entrada.getTipoEntrada(),
                entrada.getValorEntrada(),
                entrada.getDataEntrada(),
                entrada.getReciboEntrada(),
                entrada.getTipoRecorrencia().getIdRecorrencia(),
                entrada.getTipoRecorrencia().getTipoRecorrencia()
        );
    }

    private boolean isValidContentType(String contentType) {
        return contentType.equals("application/pdf") ||
               contentType.equals("image/jpeg") ||
               contentType.equals("image/png");
    }
}
