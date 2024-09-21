package dev.leaf_carvalho.gerenciador_financeiro.service;

import dev.leaf_carvalho.gerenciador_financeiro.dto.InvestimentosDTO;
import dev.leaf_carvalho.gerenciador_financeiro.exception.ResourceNotFoundException;
import dev.leaf_carvalho.gerenciador_financeiro.model.Investimentos;
import dev.leaf_carvalho.gerenciador_financeiro.model.Tipo_Recorrencia;
import dev.leaf_carvalho.gerenciador_financeiro.model.Usuarios;
import dev.leaf_carvalho.gerenciador_financeiro.repository.InvestimentosRepository;
import dev.leaf_carvalho.gerenciador_financeiro.repository.UsuariosRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class InvestimentosService {

    private final InvestimentosRepository investimentosRepository;
    private final UsuariosRepository usuariosRepository;
    private final RecorrenciaService recorrenciaService;
    private final ArmazenaArquivoService armazenaArquivoService;

    public InvestimentosService(InvestimentosRepository investimentosRepository,
                                UsuariosRepository usuariosRepository,
                                RecorrenciaService recorrenciaService,
                                ArmazenaArquivoService armazenaArquivoService) {
        this.investimentosRepository = investimentosRepository;
        this.usuariosRepository = usuariosRepository;
        this.recorrenciaService = recorrenciaService;
        this.armazenaArquivoService = armazenaArquivoService;
    }

    public List<InvestimentosDTO> getAllInvestimentosByUsuarioId(Long usuarioId) {
        return investimentosRepository.findByUsuarioId(usuarioId)
                .stream()
                .map(this::convertToDto)
                .toList();
    }

    public InvestimentosDTO getInvestimentoById(Long id) {
        Investimentos investimento = investimentosRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Investimento de ID " + id + " não encontrado."));
        return convertToDto(investimento);
    }

    @Transactional
    public InvestimentosDTO saveInvestimento(InvestimentosDTO investimentosDTO, Long usuarioId, MultipartFile file) {
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
        investimento.setTipoRecorrencia(recorrencia);
        investimento.setCategoria(investimentosDTO.getCategoria());

        if (file != null && !file.isEmpty()) {
            String contentType = file.getContentType();
            if (!isValidContentType(contentType)) {
                throw new RuntimeException("Tipo de arquivo não suportado");
            }

            String filePath = armazenaArquivoService.storeFile(file);
            investimento.setReciboInvestimento(filePath);
        } else {
            investimento.setReciboInvestimento(null);
        }

        Investimentos savedInvestimento = investimentosRepository.save(investimento);
        return convertToDto(savedInvestimento);
    }

    @Transactional
    public void deleteById(Long id) {
        investimentosRepository.deleteById(id);
    }

    private InvestimentosDTO convertToDto(Investimentos investimento) {
        return new InvestimentosDTO(
        		investimento.getId(),
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

    private boolean isValidContentType(String contentType) {
        return contentType.equals("application/pdf") ||
               contentType.equals("image/jpeg") ||
               contentType.equals("image/png");
    }
}
