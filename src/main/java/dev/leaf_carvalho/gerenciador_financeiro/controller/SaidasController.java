package dev.leaf_carvalho.gerenciador_financeiro.controller;

import dev.leaf_carvalho.gerenciador_financeiro.dto.SaidasDTO;
import dev.leaf_carvalho.gerenciador_financeiro.model.Saidas;
import dev.leaf_carvalho.gerenciador_financeiro.model.Usuarios;
import dev.leaf_carvalho.gerenciador_financeiro.service.ArmazenaArquivoService;
import dev.leaf_carvalho.gerenciador_financeiro.service.SaidasService;
import dev.leaf_carvalho.gerenciador_financeiro.exception.ResourceNotFoundException;
import dev.leaf_carvalho.gerenciador_financeiro.repository.SaidasRepository;

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
@RequestMapping("/saidas")
public class SaidasController {

    private final SaidasService saidasService;
    private final SaidasRepository saidasRepository;
    private final ArmazenaArquivoService armazenaArquivoService;

    public SaidasController(SaidasService saidasService, SaidasRepository saidasRepository, ArmazenaArquivoService armazenaArquivoService) {
        this.saidasService = saidasService;
        this.saidasRepository = saidasRepository;
        this.armazenaArquivoService = armazenaArquivoService;
    }

    @GetMapping
    public List<SaidasDTO> getAllSaidas(Authentication authentication) {
        Usuarios usuario = (Usuarios) authentication.getPrincipal();
        Long usuarioId = usuario.getId();
        return saidasService.getAllSaidasByUsuarioId(usuarioId);
    }

    @GetMapping("/{idSaida}")
    public ResponseEntity<SaidasDTO> getById(@PathVariable Long idSaida) {
        SaidasDTO saida = saidasService.getById(idSaida);
        return ResponseEntity.ok(saida);
    }

    @PostMapping
    public ResponseEntity<SaidasDTO> save(
            @ModelAttribute SaidasDTO saidaDTO,
            @RequestPart(value = "file", required = false) MultipartFile file,
            Authentication authentication) {

        Long usuarioId = ((Usuarios) authentication.getPrincipal()).getId();
        SaidasDTO newSaida = saidasService.saveSaida(saidaDTO, usuarioId, file);
        return ResponseEntity.status(201).body(newSaida);
    }

    @DeleteMapping("/{idSaida}")
    public void deleteById(@PathVariable Long idSaida) {
        saidasService.deleteById(idSaida);
    }

    @GetMapping("/downloadFile/{saidaId}")
    public ResponseEntity<Resource> downloadFile(@PathVariable Long saidaId) {
        Saidas saida = saidasRepository.findById(saidaId)
                .orElseThrow(() -> new ResourceNotFoundException("Saída não encontrada com id " + saidaId));

        if (saida.getReciboSaida() == null) {
            throw new RuntimeException("Esta saída não possui um arquivo associado.");
        }

        Resource resource = armazenaArquivoService.loadFileAsResource(saida.getReciboSaida());

        String contentType = "application/octet-stream";

        try {
            Path filePath = Path.of(saida.getReciboSaida());
            contentType = Files.probeContentType(filePath);
        } catch (IOException ex) {

        }

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }
}
