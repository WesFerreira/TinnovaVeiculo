package br.com.tinnova.TinnovaVeiculo.dto;

import jakarta.validation.constraints.*;

public record NovoVeiculoDTO(

        @NotBlank
        String veiculo,

        @NotBlank
        String marca,

        @NotNull
        @Min(value = 1000, message = "Ano do veículo deve conter 4 números")
        @Max(value = 9999, message = "Ano do veículo deve conter 4 números")
        Integer ano,

        @NotBlank
        String descricao

) {
}
