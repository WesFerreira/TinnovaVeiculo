package br.com.tinnova.TinnovaVeiculo.repository;

import br.com.tinnova.TinnovaVeiculo.entity.VeiculoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VeiculoRepository extends JpaRepository<VeiculoEntity, Long> {

    List<VeiculoEntity> findByVendidoFalse();

}
