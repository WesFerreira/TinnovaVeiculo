package br.com.tinnova.TinnovaVeiculo.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "veiculo")
public class VeiculoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String veiculo;

    private String marca;

    private Integer ano;

    @Lob
    private String descricao;

    private Boolean vendido;

    @Column(name = "created_veiculo", updatable = false)
    private LocalDateTime createdVeiculo;

    @Column(name = "updated_veiculo")
    private LocalDateTime updatedVeiculo;

}
