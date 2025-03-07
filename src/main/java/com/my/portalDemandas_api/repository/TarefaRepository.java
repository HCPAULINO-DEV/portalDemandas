package com.my.portalDemandas_api.repository;

import com.my.portalDemandas_api.domain.Tarefa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TarefaRepository extends JpaRepository<Tarefa, Long> {
}
