package id.dicka.oauth2.authservice.repository;

import id.dicka.oauth2.authservice.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepsitory extends JpaRepository<User, Integer> {

    Optional<User> findByUsername(String username);
    Optional<User> findByEmail(String email);

    @Query(value = "UPDATE User u SET u.accountLocked=:accountLocked WHERE u.id=:id")
    @Modifying
    void updateEnabledStatus(@Param("id")Integer id, @Param("accountLocked") boolean accountLocked);

    @Query(value = "UPDATE User u SET u.failedAttemps=:failedAttemps WHERE u.username=:username")
    @Modifying
    void updateUpdateFailedAttemps(@Param("failedAttemps")Integer failedAttemps, @Param("username")String username);
}
