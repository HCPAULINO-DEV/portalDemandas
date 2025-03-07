package com.my.portalDemandas_api.dto;

import com.my.portalDemandas_api.domain.Status;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CadastrarTarefaDto(
        @NotBlank
        String titulo,

        @NotBlank
        String descricao,

        @NotNull
        Status status,

        @NotNull
        Long usuario
) {
}
