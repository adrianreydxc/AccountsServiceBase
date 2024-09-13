package com.bananaapps.MyOnlineShoppingService.domain.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "accounts")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "La cuenta no puede ser nula")
    @NotEmpty(message = "La cuenta no puede estar vacía")
    @Pattern(regexp = "Personal|Company", message = "Solo se admiten cuentas Personal o Company")
    private String type;

    @NotNull
    @PastOrPresent(message = "La fecha de apertura no puede ser posterior al presente")
    private LocalDate openingDate;

    @PositiveOrZero(message = "El balance no puede ser negativo")
    private double balance;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private Customer owner;
}
