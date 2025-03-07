package com.my.portalDemandas_api.service;

import com.my.portalDemandas_api.dto.CadastrarUsuarioDto;
import com.my.portalDemandas_api.domain.Usuario;
import com.my.portalDemandas_api.repository.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;

    }

    @Transactional
    public Usuario salvarUsuario(CadastrarUsuarioDto dto) {
        Usuario usuario = new Usuario(dto);
        return usuarioRepository.save(usuario);

    }

    @Transactional
    public void deletarUsuario(Long id){
        var usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Usuario ID: " + id + " não foi encontrado"));

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

    public Usuario atualizarUsuario(Long id) {
        var usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Usuario ID: " + id + " não foi encontrado"));

        return usuarioRepository.save(usuario);

    }

}
