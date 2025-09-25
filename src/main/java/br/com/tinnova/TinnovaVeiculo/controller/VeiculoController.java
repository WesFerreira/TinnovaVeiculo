package br.com.tinnova.TinnovaVeiculo.controller;

import br.com.tinnova.TinnovaVeiculo.dto.NovoVeiculoDTO;
import br.com.tinnova.TinnovaVeiculo.entity.VeiculoEntity;
import br.com.tinnova.TinnovaVeiculo.service.VeiculoService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/veiculo")
public class VeiculoController {

    private final VeiculoService veiculoService;

    public VeiculoController(VeiculoService veiculoService) {
        this.veiculoService = veiculoService;
    }

    @PostMapping("/novoVeiculo")
    public ResponseEntity novoVeiculo(@Valid @RequestBody NovoVeiculoDTO novoVeiculoDTO) {
        veiculoService.novoVeiculo(novoVeiculoDTO);
        return ResponseEntity.ok().build();
    }

    @GetMapping("veiculoId/{id}")
    public ResponseEntity<VeiculoEntity> findByIdVeiculo(@PathVariable Long id) {
        var veiculo = veiculoService.findByIdVeiculo(id);
        return ResponseEntity.ok(veiculo.get());
    }

    @GetMapping("/listarVeiculos")
    public ResponseEntity<List<VeiculoEntity>> buscaTodosVeiculos() {
        var listVeiculos = veiculoService.buscaTodosVeiculos();
        return ResponseEntity.ok(listVeiculos);
    }

    @GetMapping("/veiculosDisponiveis")
    public ResponseEntity<List<VeiculoEntity>> buscaTodosVeiculosDisponiveis() {
        var listVeiculos = veiculoService.buscaTodosVeiculosDisponiveis();
        return ResponseEntity.ok(listVeiculos);
    }

    @DeleteMapping("/excluirVeiculo/{id}")
    public ResponseEntity excluirVeiculo(@PathVariable Long id) {
        veiculoService.excluirVeiculo(id);
        return ResponseEntity.noContent().build();
    }

}
