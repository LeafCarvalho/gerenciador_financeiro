package dev.leaf_carvalho.gerenciador_financeiro.service;

import dev.leaf_carvalho.gerenciador_financeiro.model.Usuarios;
import dev.leaf_carvalho.gerenciador_financeiro.repository.UsuariosRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuariosService {

    private final UsuariosRepository usuariosRepository;

    public UsuariosService(UsuariosRepository usuariosRepository) {
        this.usuariosRepository = usuariosRepository;
    }

    public List<Usuarios> getAllUsuarios() {
        return usuariosRepository.findAll();
    }
    
	public Usuarios getUsuario(Long idUsuario) {
        return usuariosRepository.findById(idUsuario).orElse(null);
	}

	public void addUsuarios(Usuarios usuarios) {
		usuariosRepository.save(usuarios);
	}
	
	public void deleteUsuario(Long idUsuario) {
		usuariosRepository.deleteById(idUsuario);
	}
}