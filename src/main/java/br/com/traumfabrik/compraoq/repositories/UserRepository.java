package br.com.traumfabrik.compraoq.repositories;

import br.com.traumfabrik.compraoq.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity,Long> {
    UserDetails findByUserName(String username);

    @Query("SELECT u FROM Usuario u WHERE u.userName = :userName")
    UserEntity findByEmail(@Param("userName") String username);
}
