package com.bananaapps.MyOnlineShoppingService.domain.repositories;

import com.bananaapps.MyOnlineShoppingService.domain.entities.Account;
import com.bananaapps.MyOnlineShoppingService.domain.entities.Customer;
import com.bananaapps.MyOnlineShoppingService.domain.exception.custom.AccountsByUserException;
import com.bananaapps.MyOnlineShoppingService.domain.exception.custom.InsuficientBalanceException;
import com.bananaapps.MyOnlineShoppingService.domain.exception.custom.NoSuchAccountException;
import com.bananaapps.MyOnlineShoppingService.domain.exception.custom.WithDrawnException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
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


     @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT)
    default boolean doWithDrawnEvenWithAnotherAccountUser(Long accountId, Long userId, double amount) {
        Account account = this.findById(accountId).orElseThrow(
            () -> new NoSuchAccountException("No existe una cuenta con la id " + accountId)
        );

        if (account.getBalance() < amount) {
            List<Account> accountList = getAccountsByUser(userId).orElseThrow(
                () -> new WithDrawnException("No existen cuentas para el usuario con id: " + userId)
            );

            double remainingAmount = amount;
            remainingAmount -= account.getBalance();
            account.setBalance(0);
            this.save(account);

            for (Account u : accountList) {
                if (!u.getId().equals(account.getId())) {
                    if (u.getBalance() < remainingAmount) {
                        remainingAmount -= u.getBalance();
                        u.setBalance(0);
                    } else {
                        u.setBalance(u.getBalance() - remainingAmount);
                        remainingAmount = 0;
                    }
                    this.save(u);

                    if (remainingAmount <= 0) {
                        break;
                    }
                }
            }
            if (remainingAmount > 0) {
                throw new InsuficientBalanceException("Saldo insuficiente");
            }

            return true;
        }
        return false;
    }
}
