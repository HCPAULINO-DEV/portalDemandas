package com.my.portalDemandas_api.dto;

import com.my.portalDemandas_api.domain.Tipo;
import com.my.portalDemandas_api.domain.Usuario;

public record InformarUsuarioDto(
        Long id,
        String nome,
        String email,
        String password,
        Tipo tipo
) {
    public InformarUsuarioDto(Usuario usuario){
        this(usuario.getId(), usuario.getNome(), usuario.getEmail(), usuario.getPassword(), usuario.getTipo());
    }
}
