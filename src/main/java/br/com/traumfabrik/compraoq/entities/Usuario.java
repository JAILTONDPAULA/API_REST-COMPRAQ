package br.com.traumfabrik.compraoq.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(schema = "compraoq")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Usuario implements UserDetails,Serializable {
     @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
     private Long id;
     @Column(name = "login", unique = true)
     private String  userName;
     private String  nomeCompleto;
     private String  password;
     @Column(name = "conta_ativa")
     private Boolean accountNonExpired;
     @Column(name = "conta_desbloqueada")
     private Boolean accountNonLocked;
     @Column(name = "credencial_ativa")
     private Boolean credentialsNonExpired;
     @Column(name = "habilitado")
     private Boolean enabled;

     @ManyToMany(fetch = FetchType.EAGER)
     @JoinTable(
             schema = "compraoq",
             name = "usuario_permissao",
             joinColumns = {@JoinColumn(name = "usuario_id")},
             inverseJoinColumns = {@JoinColumn(name = "permissao_id")}
     )
     private List<Permissao> permissoes = new ArrayList<>();

     public List<String> getRoles() {
          return this.permissoes
                  .stream()
                  .map(Permissao::getDescricao)
                  .collect(Collectors.toList());
     }

     @Override
     public Collection<? extends GrantedAuthority> getAuthorities() {
          return this.permissoes;
     }

     @Override
     public String getUsername() {
          return this.userName;
     }

     @Override
     public boolean isAccountNonExpired() {
          return this.accountNonExpired;
     }

     @Override
     public boolean isAccountNonLocked() {
          return this.accountNonLocked;
     }

     @Override
     public boolean isCredentialsNonExpired() {
          return this.credentialsNonExpired;
     }

     @Override
     public boolean isEnabled() {
          return this.enabled;
     }
}
