package br.com.traumfabrik.compraoq.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@Entity
@Table(schema = "compraoq", name = "usuario")
public class UserEntity implements UserDetails,Serializable {

     @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
     private Long      id;
     @Column(name = "nome_completo")
     private String    name;
     @Column(name = "login")
     private String    userName;
     private String    password;
     @Column(name = "habilitado")
     private LocalDate enabled;

     @ManyToMany(fetch = FetchType.EAGER)
     @JoinTable(
             schema = "compraoq",
             name = "usuario_permissao",
             joinColumns = {@JoinColumn(name = "id_usuario")},
             inverseJoinColumns = {@JoinColumn(name = "id_permissao")}
     )
     private List<PermissionEntity> permissions = new ArrayList<>();

     @Override
     public Collection<? extends GrantedAuthority> getAuthorities() {
          return this.permissions;
     }

     @Override
     public String getUsername() {
          return this.userName;
     }

    @Override
     public boolean isEnabled() {
          return this.enabled == null;
     }
}
