package com.my.portalDemandas_api.domain;

import com.my.portalDemandas_api.dto.AtualizarUsuarioDto;
import com.my.portalDemandas_api.dto.CadastrarUsuarioDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "usuario")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Usuario implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String email;
    private String password;
    private Tipo tipo;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Tarefa> tarefas = new ArrayList<>();

    public Usuario(CadastrarUsuarioDto dto, PasswordEncoder passwordEncoder){
        this.nome = dto.nome();
        this.email = dto.email();
        this.password = passwordEncoder.encode(dto.password());
        this.tipo = dto.tipo();

    }

    public void atualizarUsuario(AtualizarUsuarioDto dto, PasswordEncoder passwordEncoder){
        if (dto.nome() != null){
            this.nome = dto.nome();
        }
        if (dto.email() != null){
            this.email = dto.email();
        }
        if (dto.password() != null){
            this.password = passwordEncoder.encode(dto.password());
        }
        if (dto.tipo() != null){
            this.tipo = dto.tipo();
        }

    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority("ROLE_" + this.tipo.name());
        System.out.println("Usuário autenticado com papel: " + authority.getAuthority()); // Depuração
        return List.of(authority);
    }


    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
