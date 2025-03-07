package com.my.portalDemandas_api.domain;

import com.my.portalDemandas_api.dto.AtualizarTarefaDto;
import com.my.portalDemandas_api.dto.CadastrarTarefaDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tarefas")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Tarefa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;
    private String descricao;
    private Status status;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    public Tarefa(CadastrarTarefaDto dto, Usuario usuario){
        this.titulo = dto.titulo();
        this.descricao = dto.descricao();
        this.status = dto.status();
        this.usuario = usuario;
    }

    public void atualizarTarefa(AtualizarTarefaDto dto, Usuario usuario){
        if (dto.titulo() != null){
            this.titulo = dto.titulo();
        }
        if (dto.descricao() != null){
            this.descricao = dto.descricao();
        }
        if (dto.status() != null){
            this.status = dto.status();
        }
        if (dto.usuario() != null){
            this.usuario = usuario;
        }

    }
}
