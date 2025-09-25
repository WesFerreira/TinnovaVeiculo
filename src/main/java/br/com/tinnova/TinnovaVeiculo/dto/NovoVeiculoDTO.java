package br.com.tinnova.TinnovaVeiculo.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record NovoVeiculoDTO(

        @Schema(description = "Nome do veículo", example = "Civic")
        @NotBlank
        String veiculo,

        @Schema(description = "Marca do veículo", example = "Honda")
        @NotBlank
        String marca,

        @Schema(description = "Ano de fabricação", example = "2005")
        @NotNull
        @Size(min = 4, max = 4, message = "Ano do veículo deve conter 4 números")
        Integer ano,

        @Schema(description = "Descrição detalhada do veículo", example = "Carro em ótimo estado")
        @NotBlank
        String descricao

) {
}
