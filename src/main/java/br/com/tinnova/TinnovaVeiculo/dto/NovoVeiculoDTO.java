package br.com.tinnova.TinnovaVeiculo.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;

@Schema(description = "DTO de entrada para criação de veículo")
public record NovoVeiculoDTO(

        @Schema(description = "Nome veículo", example = "Civic")
        @NotBlank
        String veiculo,

        @Schema(description = "Marca do veículo", example = "Honda")
        @NotBlank
        String marca,

        @Schema(description = "Ano do veículo", example = "2005")
        @NotNull
        @Min(value = 1000, message = "Ano do veículo deve conter 4 números")
        @Max(value = 9999, message = "Ano do veículo deve conter 4 números")
        Integer ano,

        @Schema(description = "Descrição do veículo", example = "Carro em ótimo estado")
        @NotBlank
        String descricao

) {
}
