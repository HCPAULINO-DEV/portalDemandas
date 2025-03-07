package com.my.portalDemandas_api.dto;

import com.my.portalDemandas_api.domain.Status;
import com.my.portalDemandas_api.domain.Tarefa;

public record InformarTarefaDto(
        Long id,
        String titulo,
        String descricao,
        Status status,
        Long usuario
) {
    public InformarTarefaDto(Tarefa tarefa){
        this(tarefa.getId(), tarefa.getTitulo(), tarefa.getDescricao(), tarefa.getStatus(), tarefa.getUsuario().getId());
    }
}
