package dev.leaf_carvalho.gerenciador_financeiro.controller;

import dev.leaf_carvalho.gerenciador_financeiro.dto.EntradasDTO;
import dev.leaf_carvalho.gerenciador_financeiro.model.Entradas;
import dev.leaf_carvalho.gerenciador_financeiro.model.Usuarios;
import dev.leaf_carvalho.gerenciador_financeiro.service.ArmazenaArquivoService;
import dev.leaf_carvalho.gerenciador_financeiro.service.EntradasService;
import dev.leaf_carvalho.gerenciador_financeiro.exception.ResourceNotFoundException;
import dev.leaf_carvalho.gerenciador_financeiro.repository.EntradasRepository;

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
@RequestMapping("/entradas")
public class EntradasController {

    private final EntradasService entradasService;
    private final EntradasRepository entradasRepository;
    private final ArmazenaArquivoService armazenaArquivoService;

    public EntradasController(EntradasService entradasService, EntradasRepository entradasRepository, ArmazenaArquivoService armazenaArquivoService) {
        this.entradasService = entradasService;
        this.entradasRepository = entradasRepository;
        this.armazenaArquivoService = armazenaArquivoService;
    }

    @GetMapping
    public List<EntradasDTO> getAllEntradas(Authentication authentication) {
        Usuarios usuario = (Usuarios) authentication.getPrincipal();
        Long usuarioId = usuario.getId();
        return entradasService.getAllEntradasByUsuarioId(usuarioId);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntradasDTO> getEntrada(@PathVariable Long id) {
        EntradasDTO entrada = entradasService.getEntrada(id);
        return ResponseEntity.ok(entrada);
    }
    
    @GetMapping("/downloadFile/{entradaId}")
    public ResponseEntity<Resource> downloadFile(@PathVariable Long entradaId) {
        Entradas entrada = entradasRepository.findById(entradaId)
                .orElseThrow(() -> new ResourceNotFoundException("Entrada não encontrada com id " + entradaId));

        if (entrada.getReciboEntrada() == null) {
            throw new RuntimeException("Esta entrada não possui um arquivo associado.");
        }

        Resource resource = armazenaArquivoService.loadFileAsResource(entrada.getReciboEntrada());

        String contentType = "application/octet-stream";

        try {
            Path filePath = Path.of(entrada.getReciboEntrada());
            contentType = Files.probeContentType(filePath);
        } catch (IOException ex) {
        }

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }

    @PostMapping
    public ResponseEntity<EntradasDTO> saveEntrada(
            @ModelAttribute EntradasDTO entradaDTO,
            @RequestPart(value = "file", required = false) MultipartFile file,
            Authentication authentication) {

        Usuarios usuario = (Usuarios) authentication.getPrincipal();
        Long usuarioId = usuario.getId();
        EntradasDTO newEntrada = entradasService.saveEntrada(entradaDTO, usuarioId, file);
        return ResponseEntity.status(201).body(newEntrada);
    }

    @DeleteMapping("/{id}")
    public void deleteEntrada(@PathVariable Long id) {
        entradasService.deleteEntrada(id);
    }
}
