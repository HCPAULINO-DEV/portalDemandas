package com.my.portalDemandas_api.dto;

import com.my.portalDemandas_api.domain.Tipo;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CadastrarUsuarioDto(
        @NotBlank
         String nome,

         @NotBlank
         @Email(message = "E-mail inválido ou ")
         String email,

         @NotBlank
         String password,

         @NotNull
         Tipo tipo
) {
}
