package com.my.portalDemandas_api.controller;

import com.my.portalDemandas_api.domain.Usuario;
import com.my.portalDemandas_api.dto.AtualizarUsuarioDto;
import com.my.portalDemandas_api.dto.CadastrarUsuarioDto;
import com.my.portalDemandas_api.dto.InformarUsuarioDto;
import com.my.portalDemandas_api.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;

    }

    @PostMapping("/cadastrar")
    public ResponseEntity<InformarUsuarioDto> cadastrarUsuario(@RequestBody @Valid CadastrarUsuarioDto dto, UriComponentsBuilder uriComponentsBuilder) {
        var usuario = usuarioService.salvarUsuario(dto);
        var uri = uriComponentsBuilder.path("/usuarios/{id}").buildAndExpand(usuario.getId()).toUri();

        return ResponseEntity.created(uri).body(new InformarUsuarioDto(usuario));

    }

    @GetMapping
    public ResponseEntity<Page<InformarUsuarioDto>> buscarUsuarios(@PageableDefault(sort = "id", size = 10) Pageable pageable){
        Page<Usuario> usuarios = usuarioService.buscarUsuarios(pageable);
        Page<InformarUsuarioDto> pageDto = usuarios.map(InformarUsuarioDto::new);

        return ResponseEntity.ok(pageDto);

    }

    @GetMapping("/{id}")
    public ResponseEntity buscarUsuario(@PathVariable Long id){
        var usuario = usuarioService.buscarUsuario(id);

        return ResponseEntity.ok(new InformarUsuarioDto(usuario));

    }

    @PutMapping("/{id}")
    public ResponseEntity atualizarUsuario(@RequestBody @Valid AtualizarUsuarioDto dto, @PathVariable Long id){
        var usuario = usuarioService.atualizarUsuario(dto, id);

        return ResponseEntity.ok(new InformarUsuarioDto(usuario));

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarUsuario(@PathVariable Long id){
        usuarioService.deletarUsuario(id);

        return ResponseEntity.noContent().build();

    }

}