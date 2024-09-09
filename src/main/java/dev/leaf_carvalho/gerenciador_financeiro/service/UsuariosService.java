package dev.leaf_carvalho.gerenciador_financeiro.service;

import dev.leaf_carvalho.gerenciador_financeiro.model.Usuarios;
import dev.leaf_carvalho.gerenciador_financeiro.repository.UsuariosRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UsuariosService {

    private final UsuariosRepository usuariosRepository;

    public UsuariosService(UsuariosRepository usuariosRepository) {
        this.usuariosRepository = usuariosRepository;
    }

    @Transactional(readOnly = true)
    public List<Usuarios> getAllUsuarios() {
        return usuariosRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Optional<Usuarios> getUsuarioPorId(Long usuarioId) {
        return usuariosRepository.findById(usuarioId);
    }
}
