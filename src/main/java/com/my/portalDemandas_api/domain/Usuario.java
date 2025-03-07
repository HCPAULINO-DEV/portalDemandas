package com.my.portalDemandas_api.domain;

import com.my.portalDemandas_api.dto.AtualizarUsuarioDto;
import com.my.portalDemandas_api.dto.CadastrarUsuarioDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "usuario")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String email;
    private String password;
    private Tipo tipo;

    public Usuario(CadastrarUsuarioDto dto){
        this.nome = dto.nome();
        this.email = dto.email();
        this.password = dto.password();
        this.tipo = dto.tipo();

    }

    public void atualizarUsuario(AtualizarUsuarioDto dto){
        if (dto.nome() != null){
            this.nome = dto.nome();
        }
        if (dto.email() != null){
            this.email = dto.email();
        }
        if (dto.password() != null){
            this.password = dto.password();
        }
        if (dto.tipo() != null){
            this.tipo = dto.tipo();
        }

    }

}
