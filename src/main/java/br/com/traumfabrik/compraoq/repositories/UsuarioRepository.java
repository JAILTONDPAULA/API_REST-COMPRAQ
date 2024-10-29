package br.com.traumfabrik.compraoq.repositories;

import br.com.traumfabrik.compraoq.entities.Usuario;
import org.apache.catalina.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario,Long> {
    UserDetails findByUserName(String username);

    @Query("SELECT u FROM Usuario u WHERE u.userName = :userName")
    Usuario findByEmail(@Param("userName") String username);
}
