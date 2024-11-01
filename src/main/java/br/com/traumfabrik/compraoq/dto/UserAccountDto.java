package br.com.traumfabrik.compraoq.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UserAccountDto(
        @NotBlank(message = "o e-mail de acesso deve ser informado")
        @Email(message = "o campo informado deve ser um e-mail")
        String email   ,
        @NotBlank(message = "A senha deve ser infomada")
        String password
) {
}
