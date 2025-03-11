package com.my.portalDemandas_api.infra.validations;

import com.my.portalDemandas_api.dto.AtualizarUsuarioDto;
import com.my.portalDemandas_api.dto.CadastrarUsuarioDto;
import com.my.portalDemandas_api.repository.UsuarioRepository;
import org.springframework.stereotype.Component;

@Component
public class ValidarEmailUnico implements ValidadorUsuario{

    private final UsuarioRepository usuarioRepository;

    public ValidarEmailUnico(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public void validar(CadastrarUsuarioDto dtoCadastrar, AtualizarUsuarioDto dtoAtualizar) {
        if (dtoCadastrar != null && usuarioRepository.existsByEmail(dtoCadastrar.email())) {
            throw new IllegalArgumentException("E-mail já cadastrado");
        }

        // Verifica se o e-mail existe para a atualização, mas apenas se o DTO de atualização não for nulo
        if (dtoAtualizar != null && usuarioRepository.existsByEmail(dtoAtualizar.email())) {
            throw new IllegalArgumentException("E-mail já cadastrado");
        }

    }

}
