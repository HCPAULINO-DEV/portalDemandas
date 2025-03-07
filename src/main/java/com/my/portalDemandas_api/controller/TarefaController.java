package com.my.portalDemandas_api.controller;

import com.my.portalDemandas_api.domain.Tarefa;
import com.my.portalDemandas_api.dto.AtualizarTarefaDto;
import com.my.portalDemandas_api.dto.CadastrarTarefaDto;
import com.my.portalDemandas_api.dto.InformarTarefaDto;
import com.my.portalDemandas_api.service.TarefaService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/tarefas")
public class TarefaController {

    private final TarefaService tarefaService;

    public TarefaController(TarefaService tarefaService) {
        this.tarefaService = tarefaService;
    }

    @PostMapping
    public ResponseEntity<InformarTarefaDto> cadastrarTarefa(@RequestBody @Valid CadastrarTarefaDto dto, UriComponentsBuilder uriComponentsBuilder){
        var tarefa = tarefaService.salvarTarefa(dto);
        var uri = uriComponentsBuilder.path("/tarefas/{id}").buildAndExpand(tarefa.getId()).toUri();

        return ResponseEntity.created(uri).body(new InformarTarefaDto(tarefa));

    }

    @GetMapping
    public ResponseEntity<Page<InformarTarefaDto>> buscarTarefas(@PageableDefault(sort = "id", size = 10) Pageable pageable){
        Page<Tarefa> tarefas = tarefaService.buscarTarefas(pageable);
        Page<InformarTarefaDto> pageDto = tarefas.map(InformarTarefaDto::new);

        return ResponseEntity.ok(pageDto);

    }

    @GetMapping("/{id}")
    public ResponseEntity buscarTarefa(@PathVariable Long id){
        var tarefa = tarefaService.buscarTarefa(id);

        return ResponseEntity.ok(new InformarTarefaDto(tarefa));

    }

    @PutMapping("/{id}")
    public ResponseEntity<InformarTarefaDto> atualizarTarefa(@RequestBody @Valid AtualizarTarefaDto dto, @PathVariable Long id){
        var tarefa = tarefaService.atualizarTarefa(dto, id);

        return ResponseEntity.ok(new InformarTarefaDto(tarefa));

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarTarefa(@PathVariable Long id){
        tarefaService.deletarTarefa(id);

        return ResponseEntity.noContent().build();
    }
}
