package br.com.suafinanca.finance_api.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record RegistroDto (
    @NotBlank(message = "O nome e obrigatório")
    String nome,

    @NotBlank(message = "O e-mail é obrigatório")
    @Email(message = "Formato de e-mail inválido")
    String email,

    @NotBlank(message = "A senha e obrigatória")
    @Size(min = 6, message = "A senha deve ter no minimo 6 caracteres")
    String senha) {}

