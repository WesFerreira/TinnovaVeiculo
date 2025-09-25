package br.com.tinnova.TinnovaVeiculo.service;

import br.com.tinnova.TinnovaVeiculo.dto.NovoVeiculoDTO;
import br.com.tinnova.TinnovaVeiculo.entity.VeiculoEntity;
import br.com.tinnova.TinnovaVeiculo.repository.VeiculoRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class VeiculoService {

    private final VeiculoRepository veiculoRepository;

    public VeiculoService(VeiculoRepository veiculoRepository) {
        this.veiculoRepository = veiculoRepository;
    }

    public void novoVeiculo(NovoVeiculoDTO novoVeiculo) {
        var veiculo = new VeiculoEntity();
        var dataAtual = LocalDateTime.now();
        veiculo.setVeiculo(novoVeiculo.veiculo());
        veiculo.setMarca(novoVeiculo.marca());
        veiculo.setAno(novoVeiculo.ano());
        veiculo.setDescricao(novoVeiculo.descricao());
        veiculo.setVendido(false);
        veiculo.setCreatedVeiculo(dataAtual);
        veiculo.setUpdatedVeiculo(dataAtual);
        veiculoRepository.save(veiculo);
    }

}
