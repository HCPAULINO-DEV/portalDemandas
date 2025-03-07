package com.my.portalDemandas_api.service;

import com.my.portalDemandas_api.domain.Tarefa;
import com.my.portalDemandas_api.domain.Usuario;
import com.my.portalDemandas_api.dto.AtualizarTarefaDto;
import com.my.portalDemandas_api.dto.CadastrarTarefaDto;
import com.my.portalDemandas_api.repository.TarefaRepository;
import com.my.portalDemandas_api.repository.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TarefaService {

    private final TarefaRepository tarefaRepository;

    private final UsuarioRepository usuarioRepository;

    public TarefaService(TarefaRepository tarefaRepository, UsuarioRepository usuarioRepository) {
        this.tarefaRepository = tarefaRepository;
        this.usuarioRepository = usuarioRepository;
    }

    @Transactional
    public Tarefa salvarTarefa(@Valid CadastrarTarefaDto dto) {
        Usuario usuario = usuarioRepository.findById(dto.usuario())
                .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado"));

        Tarefa tarefa = new Tarefa(dto, usuario);

        return tarefaRepository.save(tarefa);

    }

    public Page<Tarefa> buscarTarefas(Pageable pageable){
        Page<Tarefa> tarefas = tarefaRepository.findAll(pageable);
        if (tarefas.isEmpty()){
            throw new EntityNotFoundException("Tarefas não encontradas");
        }

        return tarefas;

    }

    public Tarefa buscarTarefa(Long id){
        var tarefa = tarefaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Tarefa ID: " + id + " não foi encontrada"));

        return tarefa;

    }

    @Transactional
    public Tarefa atualizarTarefa(AtualizarTarefaDto dto, Long id){
        Usuario usuario = usuarioRepository.findById(dto.usuario())
                .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado"));

        var tarefa = tarefaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Tarefa ID: " + id + " não foi encontrada"));

        tarefa.atualizarTarefa(dto, usuario);

        return tarefaRepository.save(tarefa);

    }

    @Transactional
    public void deletarTarefa(Long id){
        var tarefa = tarefaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Tarefa ID: " + id + " não foi encontrada"));

        tarefaRepository.delete(tarefa);

    }

}
