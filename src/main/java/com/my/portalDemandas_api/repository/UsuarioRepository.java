package com.my.portalDemandas_api.repository;

import com.my.portalDemandas_api.domain.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Boolean existsByEmail(String email);

    UserDetails findByEmail(String email);
}
