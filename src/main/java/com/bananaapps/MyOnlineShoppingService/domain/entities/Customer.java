package com.bananaapps.MyOnlineShoppingService.domain.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "customers")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "El nombre no puede ser null")
    @NotEmpty(message = "El nombre es obligatorio, no puede estar vacío")
    @Size(min = 2, max = 80, message = "Nombre entre 2 y 80 caracteres")
    private String name;

    @NotNull(message = "El email no puede ser null")
    @Email(message= "El email debe tener un formato váldio")
    private String email;
}
