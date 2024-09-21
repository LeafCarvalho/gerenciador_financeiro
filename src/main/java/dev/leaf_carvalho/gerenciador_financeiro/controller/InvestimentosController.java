package dev.leaf_carvalho.gerenciador_financeiro.controller;

import dev.leaf_carvalho.gerenciador_financeiro.dto.InvestimentosDTO;
import dev.leaf_carvalho.gerenciador_financeiro.model.Investimentos;
import dev.leaf_carvalho.gerenciador_financeiro.model.Usuarios;
import dev.leaf_carvalho.gerenciador_financeiro.service.ArmazenaArquivoService;
import dev.leaf_carvalho.gerenciador_financeiro.service.InvestimentosService;
import dev.leaf_carvalho.gerenciador_financeiro.exception.ResourceNotFoundException;
import dev.leaf_carvalho.gerenciador_financeiro.repository.InvestimentosRepository;

import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/investimentos")
public class InvestimentosController {

    private final InvestimentosService investimentosService;
    private final InvestimentosRepository investimentosRepository;
    private final ArmazenaArquivoService armazenaArquivoService;

    public InvestimentosController(InvestimentosService investimentosService,
                                   InvestimentosRepository investimentosRepository,
                                   ArmazenaArquivoService armazenaArquivoService) {
        this.investimentosService = investimentosService;
        this.investimentosRepository = investimentosRepository;
        this.armazenaArquivoService = armazenaArquivoService;
    }

    @GetMapping
    public List<InvestimentosDTO> getAllInvestimentos(Authentication authentication) {
        Usuarios usuario = (Usuarios) authentication.getPrincipal();
        Long usuarioId = usuario.getId();
        return investimentosService.getAllInvestimentosByUsuarioId(usuarioId);
    }

    @GetMapping("/{id}")
    public ResponseEntity<InvestimentosDTO> getInvestimentoById(@PathVariable Long id) {
        InvestimentosDTO investimento = investimentosService.getInvestimentoById(id);
        return ResponseEntity.ok(investimento);
    }

    @PostMapping
    public ResponseEntity<InvestimentosDTO> saveInvestimento(
            @ModelAttribute InvestimentosDTO investimentosDTO,
            @RequestPart(value = "file", required = false) MultipartFile file,
            Authentication authentication) {

        Long usuarioId = ((Usuarios) authentication.getPrincipal()).getId();
        InvestimentosDTO newInvestimento = investimentosService.saveInvestimento(investimentosDTO, usuarioId, file);
        return ResponseEntity.status(201).body(newInvestimento);
    }

    @DeleteMapping("/{id}")
    public void deleteInvestimento(@PathVariable Long id) {
        investimentosService.deleteById(id);
    }

    @GetMapping("/downloadFile/{investimentoId}")
    public ResponseEntity<Resource> downloadFile(@PathVariable Long investimentoId) {
        Investimentos investimento = investimentosRepository.findById(investimentoId)
                .orElseThrow(() -> new ResourceNotFoundException("Investimento não encontrado com id " + investimentoId));

        if (investimento.getReciboInvestimento() == null) {
            throw new RuntimeException("Este investimento não possui um arquivo associado.");
        }

        Resource resource = armazenaArquivoService.loadFileAsResource(investimento.getReciboInvestimento());

        String contentType = "application/octet-stream";

        try {
            Path filePath = Path.of(investimento.getReciboInvestimento());
            contentType = Files.probeContentType(filePath);
        } catch (IOException ex) {

        }

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }
}
