package com.my.portalDemandas_api.infra.validations;

import com.my.portalDemandas_api.dto.AtualizarTarefaDto;
import com.my.portalDemandas_api.dto.CadastrarTarefaDto;

public interface ValidadorTarefa {

    public void validar(CadastrarTarefaDto dtoCadastrar, AtualizarTarefaDto dtoAtualizar);

}
