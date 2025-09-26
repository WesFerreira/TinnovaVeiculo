package br.com.tinnova.TinnovaVeiculo.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

public record AtualizaVeiculoDTO(

        Long id,

        String veiculo,

        String marca,

        @Min(value = 1000, message = "Ano do veículo deve conter 4 números")
        @Max(value = 9999, message = "Ano do veículo deve conter 4 números")
        Integer ano,

        String descricao,

        Boolean vendido

) {
}
