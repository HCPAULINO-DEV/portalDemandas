package com.my.portalDemandas_api.infra.validations;

import com.my.portalDemandas_api.dto.AtualizarUsuarioDto;
import com.my.portalDemandas_api.dto.CadastrarUsuarioDto;

public interface ValidadorUsuario {

    public void validar(CadastrarUsuarioDto dtoCadastrar, AtualizarUsuarioDto dtoAtualizar);

}
