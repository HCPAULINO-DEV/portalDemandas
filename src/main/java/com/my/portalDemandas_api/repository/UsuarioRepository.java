package com.my.portalDemandas_api.repository;

import com.my.portalDemandas_api.domain.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}
