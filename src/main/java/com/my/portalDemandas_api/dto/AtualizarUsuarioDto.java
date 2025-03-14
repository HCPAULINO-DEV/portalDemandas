package com.my.portalDemandas_api.dto;

import com.my.portalDemandas_api.domain.Tipo;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record AtualizarUsuarioDto(
        @NotBlank
        String nome,

        @NotBlank
        @Email(message = "Insira um e-mail válido")
        String email,

        @NotBlank
        String password,

        @NotNull
        Tipo tipo
) {
}
