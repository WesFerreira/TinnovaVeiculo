package br.com.tinnova.TinnovaVeiculo.repository;

import br.com.tinnova.TinnovaVeiculo.entity.VeiculoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface VeiculoRepository extends JpaRepository<VeiculoEntity, Long> {

    List<VeiculoEntity> findByVendidoFalse();

    @Query(value = " SELECT v FROM VeiculoEntity v " +
        " WHERE (:veiculo IS NULL OR LOWER(v.veiculo) = LOWER(:veiculo)) " +
        "  AND (:ano IS NULL OR v.ano = :ano) " +
        "  AND (:marca IS NULL OR LOWER(v.marca) = LOWER(:marca)) ")
    List<VeiculoEntity> buscaDetalhadaVeiculos(@Param("veiculo") String veiculo,
                                               @Param("ano") Integer ano,
                                               @Param("marca") String marca);

    Long countByVendidoFalse();

}
