package br.com.tinnova.TinnovaVeiculo.service;

import br.com.tinnova.TinnovaVeiculo.dto.NovoVeiculoDTO;
import br.com.tinnova.TinnovaVeiculo.entity.VeiculoEntity;
import br.com.tinnova.TinnovaVeiculo.exception.VeiculoIdNotFoundException;
import br.com.tinnova.TinnovaVeiculo.repository.VeiculoRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

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

    public void excluirVeiculo(Long id) {
        Optional<VeiculoEntity> veiculo = getVeiculoEntity(id);
        veiculoRepository.delete(veiculo.get());
    }

    public Optional<VeiculoEntity> findByIdVeiculo(Long id) {
        return getVeiculoEntity(id);
    }

    private Optional<VeiculoEntity> getVeiculoEntity(Long id) {
        var veiculo = veiculoRepository.findById(id);
        if (veiculo.isEmpty()) {
            throw new VeiculoIdNotFoundException("Veiculo com o id não foi encontrado");
        }
        return veiculo;
    }
}
