package dev.leaf_carvalho.gerenciador_financeiro.service;

import java.util.List;
import org.springframework.stereotype.Service;
import dev.leaf_carvalho.gerenciador_financeiro.model.Usuarios;
import dev.leaf_carvalho.gerenciador_financeiro.repository.UsuariosRepository;

@Service
public class UsuariosService {

    private final UsuariosRepository usuariosRepository;

    public UsuariosService(UsuariosRepository usuariosRepository) {
        this.usuariosRepository = usuariosRepository;
    }

    public List<Usuarios> getAllSaidas() {
        return usuariosRepository.findAll();
    }
}
