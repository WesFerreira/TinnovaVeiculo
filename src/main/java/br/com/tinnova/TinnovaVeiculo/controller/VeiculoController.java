package br.com.tinnova.TinnovaVeiculo.controller;

import br.com.tinnova.TinnovaVeiculo.dto.NovoVeiculoDTO;
import br.com.tinnova.TinnovaVeiculo.service.VeiculoService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @DeleteMapping("/excluirVeiculo/{id}")
    public ResponseEntity excluirVeiculo(@PathVariable Long id) {
        veiculoService.excluirVeiculo(id);
        return ResponseEntity.noContent().build();
    }

}
