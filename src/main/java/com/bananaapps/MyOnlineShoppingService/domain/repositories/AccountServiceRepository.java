package com.bananaapps.MyOnlineShoppingService.domain.repositories;

import com.bananaapps.MyOnlineShoppingService.domain.entities.Account;
import com.bananaapps.MyOnlineShoppingService.domain.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface AccountServiceRepository extends JpaRepository<Account, Long> {
    @Query("SELECT u FROM Account u WHERE u.owner.id = :id")
    Optional<List<Account>> getAccountsByUser(@Param("id") Long id);

    @Transactional
    @Modifying
    @Query("DELETE FROM Account u WHERE u.owner.id = :id")
    void deleteAllAccountsByUser(@Param("id") Long id);

}
