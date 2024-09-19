package com.bananaapps.MyOnlineShoppingService.domain.repositories;

import com.bananaapps.MyOnlineShoppingService.domain.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query("SELECT u FROM User u WHERE u.username = :username AND u.password = :password")
    Optional<User> getUserByEmail(@Param("username") String username,
                                  @Param("password") String password);
}
