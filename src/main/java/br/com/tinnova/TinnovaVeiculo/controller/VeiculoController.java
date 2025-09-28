package br.com.tinnova.TinnovaVeiculo.controller;

import br.com.tinnova.TinnovaVeiculo.dto.AtualizaVeiculoDTO;
import br.com.tinnova.TinnovaVeiculo.dto.DistribuicaoMarcaDTO;
import br.com.tinnova.TinnovaVeiculo.dto.NovoVeiculoDTO;
import br.com.tinnova.TinnovaVeiculo.entity.VeiculoEntity;
import br.com.tinnova.TinnovaVeiculo.service.VeiculoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/veiculo")
@Tag(name = "Veículos", description = "Operações relacionadas a veículos")
public class VeiculoController {

    private final VeiculoService veiculoService;

    public VeiculoController(VeiculoService veiculoService) {
        this.veiculoService = veiculoService;
    }

    @PostMapping("/novoVeiculo")
    @Operation(
            summary = "Cria um novo veículo",
            description = "Recebe os dados do veículo e persiste no banco"
    )
    @ApiResponse(responseCode = "201", description = "Veículo criado com sucesso")
    @ApiResponse(responseCode = "400", description = "Dados inválidos")
    public ResponseEntity novoVeiculo(@Valid @RequestBody NovoVeiculoDTO novoVeiculoDTO) {
        veiculoService.novoVeiculo(novoVeiculoDTO);
        return ResponseEntity.ok().build();
    }

    @GetMapping("veiculoId/{id}")
    @Operation(
            summary = "Busca um veículo por ID",
            description = "Retorna os dados do veículo correspondente ao ID informado"
    )
    @ApiResponse(responseCode = "200", description = "Veículo encontrado")
    @ApiResponse(responseCode = "404", description = "Veículo não encontrado")
    public ResponseEntity<VeiculoEntity> findByIdVeiculo(@PathVariable Long id) {
        var veiculo = veiculoService.findByIdVeiculo(id);
        return ResponseEntity.ok(veiculo.get());
    }

    @GetMapping("/listarVeiculos")
    @Operation(
            summary = "Busca todos os veículo",
            description = "Retorna os dados de todos os veículo"
    )
    @ApiResponse(responseCode = "200", description = "Veículos encontrados")
    public ResponseEntity<List<VeiculoEntity>> buscaTodosVeiculos() {
        var listVeiculos = veiculoService.buscaTodosVeiculos();
        return ResponseEntity.ok(listVeiculos);
    }

    @GetMapping("/veiculosDisponiveis")
    @Operation(
            summary = "Busca todos os veículo disponíveis para venda",
            description = "Retorna os dados de todos os veículo disponíveis para venda"
    )
    @ApiResponse(responseCode = "200", description = "Veículos encontrados")
    public ResponseEntity<List<VeiculoEntity>> buscaTodosVeiculosDisponiveis() {
        var listVeiculos = veiculoService.buscaTodosVeiculosDisponiveis();
        return ResponseEntity.ok(listVeiculos);
    }

    @GetMapping("/buscaDetalhadaVeiculos")
    @Operation(
            summary = "Busca todos os veículo disponíveis conforme os dados passados",
            description = "Retorna os dados de todos os veículo encontrados"
    )
    @ApiResponse(responseCode = "200", description = "Veículos encontrados")
    public ResponseEntity<List<VeiculoEntity>> buscaDetalhadaVeiculos(@RequestParam(required = false) String veiculo,
                                                                      @RequestParam(required = false) Integer ano,
                                                                      @RequestParam(required = false) String marca) {
        var listVeiculos = veiculoService.buscaDetalhadaVeiculos(veiculo, ano, marca);
        return ResponseEntity.ok(listVeiculos);
    }

    @GetMapping("/countVeiculosDisponiveis")
    @Operation(
            summary = "Retorna a soma de todos os veículo disponíveis",
            description = "Retorna a soma dos veículos encontrados"
    )
    @ApiResponse(responseCode = "200", description = "soma dos veículos")
    public ResponseEntity<Long> countVeiculosDisponiveis() {
        var count = veiculoService.countVeiculosDisponiveis();
        return ResponseEntity.ok(count);
    }

    @GetMapping("/distribuicaoMarcas")
    @Operation(
            summary = "Retorna a soma de todos os veículo disponíveis por cada marca",
            description = "Retorna a soma dos veículos por marca"
    )
    @ApiResponse(responseCode = "200", description = "soma dos veículos por marca")
    public ResponseEntity<List<DistribuicaoMarcaDTO>> distribuicaoPorMarcas() {
        var count = veiculoService.distribuicaoPorMarca();
        return ResponseEntity.ok(count);
    }

    @PutMapping("/atualizaVeiculos")
    @Operation(
            summary = "Atualiza um veículo",
            description = "Atualiza um veículo conforme os dados passados"
    )
    @ApiResponse(responseCode = "200", description = "véiculo atualizado com sucesso")
    @ApiResponse(responseCode = "404", description = "veículo não encontrado")
    public ResponseEntity<VeiculoEntity> atualizaVeiculos(@RequestBody AtualizaVeiculoDTO veiculoAtualizadoDTO) {
        var veiculoAtualizado = veiculoService.atualizaVeiculos(veiculoAtualizadoDTO);
        return ResponseEntity.ok(veiculoAtualizado);
    }

    @DeleteMapping("/excluirVeiculo/{id}")
    @Operation(
            summary = "Exclui um veículo por ID",
            description = "Exclui o véiculo ID informado"
    )
    @ApiResponse(responseCode = "204", description = "Veículo encontrado")
    @ApiResponse(responseCode = "404", description = "Veículo não encontrado")
    public ResponseEntity excluirVeiculo(@PathVariable Long id) {
        veiculoService.excluirVeiculo(id);
        return ResponseEntity.noContent().build();
    }

}
