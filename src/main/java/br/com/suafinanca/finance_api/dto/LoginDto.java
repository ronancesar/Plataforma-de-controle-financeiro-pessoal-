package br.com.suafinanca.finance_api.dto;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;


public record LoginDto (
        @NotBlank(message = "O e-mail e obrigatório")
        @Email(message = "Formato de e-mail inválido")
        String email,

        @NotBlank(message = "A senha e obrigatória")
        String senha
){}

