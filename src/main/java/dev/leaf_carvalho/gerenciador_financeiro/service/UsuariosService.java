package dev.leaf_carvalho.gerenciador_financeiro.service;

import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dev.leaf_carvalho.gerenciador_financeiro.model.Usuarios;
import dev.leaf_carvalho.gerenciador_financeiro.repository.UsuariosRepository;

@Service
public class UsuariosService {

    private final UsuariosRepository usuariosRepository;

    public UsuariosService(UsuariosRepository usuariosRepository) {
        this.usuariosRepository = usuariosRepository;
    }

    @Transactional
	public List<Usuarios> getAllSaidas() {
	    List<Usuarios> usuarios = usuariosRepository.findAll();
	    for (Usuarios usuario : usuarios) {
	        usuario.getSaidas().size();
	        usuario.getInvestimentos().size();
	        usuario.getEntradas().size();
	    }
	    return usuarios;
	}

}
