package dev.leaf_carvalho.gerenciador_financeiro.service;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;

import java.nio.file.*;
import java.io.IOException;
import java.net.MalformedURLException;

@Service
public class ArmazenaArquivoService {

    private final Path fileStorageLocation;

    public ArmazenaArquivoService() {
        this.fileStorageLocation = Paths.get("uploads")
                .toAbsolutePath().normalize();

        try {
            Files.createDirectories(this.fileStorageLocation);
        } catch (Exception ex) {
            throw new RuntimeException("Não foi possível criar o diretório para upload de arquivos.", ex);
        }
    }

    public String storeFile(MultipartFile file) {
        // Normalize o nome do arquivo
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        try {
            // Verifique se o nome do arquivo contém caracteres inválidos
            if (fileName.contains("..")) {
                throw new RuntimeException("Nome de arquivo inválido: " + fileName);
            }

            // Copie o arquivo para o local de destino
            Path targetLocation = this.fileStorageLocation.resolve(fileName);
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

            // Retorne o caminho relativo para armazenar no banco de dados
            return targetLocation.toString();
        } catch (IOException ex) {
            throw new RuntimeException("Não foi possível armazenar o arquivo " + fileName + ". Por favor, tente novamente!", ex);
        }
    }
    
    public Resource loadFileAsResource(String filePath) {
        try {
            Path file = Paths.get(filePath).normalize();
            Resource resource = new UrlResource(file.toUri());
            if (resource.exists() || resource.isReadable()) {
                return resource;
            } else {
                throw new RuntimeException("Arquivo não encontrado: " + filePath);
            }
        } catch (MalformedURLException ex) {
            throw new RuntimeException("Arquivo não encontrado: " + filePath, ex);
        }
    }

}
