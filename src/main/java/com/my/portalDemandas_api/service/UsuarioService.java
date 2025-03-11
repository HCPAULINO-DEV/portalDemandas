package com.my.portalDemandas_api.service;

import com.my.portalDemandas_api.dto.AtualizarTarefaDto;
import com.my.portalDemandas_api.dto.AtualizarUsuarioDto;
import com.my.portalDemandas_api.dto.CadastrarUsuarioDto;
import com.my.portalDemandas_api.domain.Usuario;
import com.my.portalDemandas_api.infra.validations.ValidadorUsuario;
import com.my.portalDemandas_api.repository.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import jdk.dynalink.linker.LinkerServices;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UsuarioService {

    private final List<ValidadorUsuario> validadores;

    private final UsuarioRepository usuarioRepository;

    public UsuarioService(List<ValidadorUsuario> validadores, UsuarioRepository usuarioRepository) {
        this.validadores = validadores;
        this.usuarioRepository = usuarioRepository;
    }

    @Transactional
    public Usuario salvarUsuario(CadastrarUsuarioDto dtoCadastrar) {
        validadores.forEach(v -> v.validar(dtoCadastrar, null));
        Usuario usuario = new Usuario(dtoCadastrar);

        return usuarioRepository.save(usuario);

    }

    @Transactional
    public void deletarUsuario(Long id){
        var usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Usuario ID: " + id + " não foi encontrado"));

        if (!usuario.getTarefas().isEmpty()){
            throw new EntityNotFoundException("O usuário possuí tarefas criadas por isso não será possível realizar a exclusão!");
        }

        usuarioRepository.delete(usuario);

    }

    public Page<Usuario> buscarUsuarios(Pageable pageable){
        Page<Usuario> usuarios = usuarioRepository.findAll(pageable);

        if (usuarios.isEmpty()){
            throw new EntityNotFoundException("Usuarios não encontrados");
        }

        return usuarios;

    }

    public Usuario buscarUsuario(Long id){
        var usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Usuario ID: " + id + " não foi encontrado"));

        return usuario;

    }

    @Transactional
    public Usuario atualizarUsuario(AtualizarUsuarioDto dtoAtualizar, Long id) {
        validadores.forEach(v -> v.validar(null, dtoAtualizar));
        var usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Usuario ID: " + id + " não foi encontrado"));

        usuario.atualizarUsuario(dtoAtualizar);

        return usuarioRepository.save(usuario);

    }

}
