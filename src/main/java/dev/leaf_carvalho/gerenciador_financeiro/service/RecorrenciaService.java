package dev.leaf_carvalho.gerenciador_financeiro.service;

import java.util.List;

import org.springframework.stereotype.Service;

import dev.leaf_carvalho.gerenciador_financeiro.model.Tipo_Recorrencia;
import dev.leaf_carvalho.gerenciador_financeiro.repository.RecorrenciaRepository;
import jakarta.transaction.Transactional;


@Service
public class RecorrenciaService {
    private final RecorrenciaRepository recorrenciaRepository;

    public RecorrenciaService(RecorrenciaRepository investimentosRepository) {
        this.recorrenciaRepository = investimentosRepository;
    }

    @Transactional
    public List<Tipo_Recorrencia> getAllRecorrencias() {
        return recorrenciaRepository.findAll();
    }

    @Transactional
    public Tipo_Recorrencia getRecorrenciaById(Long id) {
        return recorrenciaRepository.findById(id).orElseThrow();
    }

    @Transactional
    public Tipo_Recorrencia saveRecorrencia(Tipo_Recorrencia recorrencia) {
        return recorrenciaRepository.save(recorrencia);
    }

    @Transactional
    public void deleteRecorrencia(Long id) {
        recorrenciaRepository.deleteById(id);
    }
}

