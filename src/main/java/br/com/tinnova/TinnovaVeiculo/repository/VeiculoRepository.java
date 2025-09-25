package br.com.tinnova.TinnovaVeiculo.repository;

import br.com.tinnova.TinnovaVeiculo.entity.VeiculoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VeiculoRepository extends JpaRepository<VeiculoEntity, Long> {
}
