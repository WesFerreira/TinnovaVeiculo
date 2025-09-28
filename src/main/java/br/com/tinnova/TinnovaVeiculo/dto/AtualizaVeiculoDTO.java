package br.com.tinnova.TinnovaVeiculo.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

@Schema(description = "DTO de atualização de um veículo")
public record AtualizaVeiculoDTO(

        @NotNull
        @Schema(description = "Id do veículo", example = "1L")
        Long id,

        @Schema(description = "Nome veículo", example = "Civic")
        String veiculo,

        @Schema(description = "Marca do veículo", example = "Honda")
        String marca,

        @Schema(description = "Ano do veículo", example = "2005")
        @Min(value = 1000, message = "Ano do veículo deve conter 4 números")
        @Max(value = 9999, message = "Ano do veículo deve conter 4 números")
        Integer ano,

        @Schema(description = "Descrição do veículo", example = "Carro em ótimo estado")
        String descricao,

        @Schema(description = "Veículo vendido ou não", example = "false")
        Boolean vendido

) {
}
