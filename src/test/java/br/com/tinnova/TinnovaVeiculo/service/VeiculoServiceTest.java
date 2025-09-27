package br.com.tinnova.TinnovaVeiculo.service;

import br.com.tinnova.TinnovaVeiculo.dto.AtualizaVeiculoDTO;
import br.com.tinnova.TinnovaVeiculo.dto.DistribuicaoMarcaDTO;
import br.com.tinnova.TinnovaVeiculo.dto.NovoVeiculoDTO;
import br.com.tinnova.TinnovaVeiculo.entity.VeiculoEntity;
import br.com.tinnova.TinnovaVeiculo.exception.VeiculoIdNotFoundException;
import br.com.tinnova.TinnovaVeiculo.repository.VeiculoRepository;
import br.com.tinnova.TinnovaVeiculo.repository.projection.VeiculoMarcaProjection;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class VeiculoServiceTest {

    @InjectMocks
    private VeiculoService veiculoService;

    @Mock
    private VeiculoRepository veiculoRepository;

    LocalDateTime dataAtual;
    NovoVeiculoDTO novoVeiculoDTO;
    VeiculoEntity veiculo;

    @BeforeEach
    public void setUp() {
        dataAtual = LocalDateTime.now();
        novoVeiculoDTO = new NovoVeiculoDTO("Civic", "Honda", 2005, "ótimo estado");

        veiculo = new VeiculoEntity();
        veiculo.setVeiculo("Civic");
        veiculo.setMarca("Honda");
        veiculo.setAno(2005);
        veiculo.setDescricao("ótimo estado");
        veiculo.setVendido(false);
        veiculo.setCreatedVeiculo(dataAtual);
        veiculo.setUpdatedVeiculo(dataAtual);
    }

    @Test
    public void novoVeiculo__should_return_created_when_ok() {

        when(veiculoRepository.save(any(VeiculoEntity.class))).thenReturn(any(VeiculoEntity.class));

        veiculoService.novoVeiculo(novoVeiculoDTO);
        verify(veiculoRepository, times(1)).save(any(VeiculoEntity.class));
    }

    @Test
    public void findByIdVeiculo__should_return_optional_veiculo() {
        when(veiculoRepository.findById(1L)).thenReturn(Optional.of(veiculo));

        Optional<VeiculoEntity> result = veiculoService.findByIdVeiculo(1L);

        assertTrue(result.isPresent());
        assertEquals(veiculo, result.get());
    }

    @Test
    public void findByIdVeiculo__should_return_notFound_when_id_is_invalid() {
        when(veiculoRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(VeiculoIdNotFoundException.class, () -> veiculoService.findByIdVeiculo(1L));
    }

    @Test
    public void buscaTodosVeiculos__should_return_list() {
        when(veiculoRepository.findAll()).thenReturn(List.of(veiculo));

        List<VeiculoEntity> result = veiculoService.buscaTodosVeiculos();

        assertFalse(result.isEmpty());
        assertEquals(1, result.size());
        assertEquals(veiculo, result.get(0));
    }

    @Test
    public void buscaTodosVeiculosDisponiveis__should_return_list() {
        when(veiculoRepository.findByVendidoFalse()).thenReturn(List.of(veiculo));

        List<VeiculoEntity> result = veiculoService.buscaTodosVeiculosDisponiveis();

        assertFalse(result.isEmpty());
        assertEquals(1, result.size());
        assertEquals(veiculo, result.get(0));
    }

    @Test
    public void countVeiculosDisponiveis__should_return_list() {
        when(veiculoRepository.countByVendidoFalse()).thenReturn(5L);

        Long result = veiculoService.countVeiculosDisponiveis();

        assertEquals(5L, result);
    }

    @Test
    public void distribuicaoPorMarca__should_return_dto_list() {
        VeiculoMarcaProjection projection = mock(VeiculoMarcaProjection.class);
        when(projection.getMarca()).thenReturn("Toyota");
        when(projection.getTotal()).thenReturn(5L);

        when(veiculoRepository.countByMarca()).thenReturn(List.of(projection));

        List<DistribuicaoMarcaDTO> result = veiculoService.distribuicaoPorMarca();

        assertThat(result)
                .isNotEmpty()
                .hasSize(1)
                .first()
                .satisfies(dto -> {
                    assertThat(dto.marca()).isEqualTo("Toyota");
                    assertThat(dto.total()).isEqualTo(5L);
                });
    }

    @Test
    public void distribuicaoPorMarca__should_return_empty_list_when_no_data() {
        when(veiculoRepository.countByMarca()).thenReturn(Collections.emptyList());

        List<DistribuicaoMarcaDTO> result = veiculoService.distribuicaoPorMarca();

        assertThat(result).isEmpty();
    }

    @Test
    void excluirVeiculo__should_delete_when_exists() {
        VeiculoEntity veiculo = new VeiculoEntity();
        veiculo.setId(1L);

        when(veiculoRepository.findById(1L)).thenReturn(Optional.of(veiculo));

        veiculoService.excluirVeiculo(1L);

        verify(veiculoRepository, times(1)).delete(veiculo);
    }

    @Test
    void excluirVeiculo__should_throwException_when_notFound() {
        when(veiculoRepository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(VeiculoIdNotFoundException.class,
                () -> veiculoService.excluirVeiculo(99L));

        verify(veiculoRepository, never()).delete(any());
    }

    @Test
    void atualizaVeiculos__should_update_fields_and_save() {
        AtualizaVeiculoDTO dto = new AtualizaVeiculoDTO(
                1L,
                "Uno",
                "Fiat",
                2010,
                "Carro popular",
                true
        );

        when(veiculoRepository.findById(1L)).thenReturn(Optional.of(veiculo));
        when(veiculoRepository.save(any(VeiculoEntity.class))).thenAnswer(invocation -> invocation.getArgument(0));

        VeiculoEntity atualizado = veiculoService.atualizaVeiculos(dto);

        assertEquals("Uno", atualizado.getVeiculo());
        assertEquals("Fiat", atualizado.getMarca());
        assertEquals(2010, atualizado.getAno());
        assertEquals("Carro popular", atualizado.getDescricao());
        assertTrue(atualizado.getVendido());
        assertNotNull(atualizado.getUpdatedVeiculo());

        verify(veiculoRepository, times(1)).save(veiculo);
    }

    @Test
    void atualizaVeiculos__should_throwException_when_notFound() {
        AtualizaVeiculoDTO dto = new AtualizaVeiculoDTO(
                99L,
                "Uno",
                "Fiat",
                2010,
                "Carro popular",
                true);

        when(veiculoRepository.findById(99L)).thenReturn(Optional.empty());

        RuntimeException ex = assertThrows(RuntimeException.class,
                () -> veiculoService.atualizaVeiculos(dto));

        assertEquals("Veiculo com o id não foi encontrado", ex.getMessage());
        verify(veiculoRepository, never()).save(any());
    }

    @Test
    void atualizaVeiculos__should_update_any_fields() {
        AtualizaVeiculoDTO dto = new AtualizaVeiculoDTO(
                1L,
                null,
                "Fiat",
                null,
                null,
                true);

        when(veiculoRepository.findById(1L)).thenReturn(Optional.of(veiculo));
        when(veiculoRepository.save(any(VeiculoEntity.class))).thenAnswer(invocation -> invocation.getArgument(0));

        VeiculoEntity atualizado = veiculoService.atualizaVeiculos(dto);

        assertEquals("Civic", atualizado.getVeiculo());
        assertEquals("Fiat", atualizado.getMarca());
        assertEquals(2005, atualizado.getAno());
        assertEquals("ótimo estado", atualizado.getDescricao());
        assertTrue(atualizado.getVendido());
        assertNotNull(atualizado.getUpdatedVeiculo());
    }

}
