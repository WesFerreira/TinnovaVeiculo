package br.com.tinnova.TinnovaVeiculo.controller;

import br.com.tinnova.TinnovaVeiculo.dto.AtualizaVeiculoDTO;
import br.com.tinnova.TinnovaVeiculo.dto.DistribuicaoMarcaDTO;
import br.com.tinnova.TinnovaVeiculo.dto.NovoVeiculoDTO;
import br.com.tinnova.TinnovaVeiculo.entity.VeiculoEntity;
import br.com.tinnova.TinnovaVeiculo.service.VeiculoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;

import static org.mockito.Mockito.*;

@WebMvcTest(VeiculoController.class)
public class VeiculoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private VeiculoService veiculoService;

    @Test
    void novoVeiculo__should_return_created() throws Exception {
        NovoVeiculoDTO dto = new NovoVeiculoDTO("Fusca", "Volkswagen", 1980, "unico dono");
        doNothing().when(veiculoService).novoVeiculo(dto);

        mockMvc.perform(post("/veiculo/novoVeiculo")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isOk());
    }

    @Test
    void findByIdVeiculo__should_return_veiculo() throws Exception {
        VeiculoEntity veiculo = new VeiculoEntity();
        veiculo.setId(1L);
        veiculo.setVeiculo("Fusca");
        veiculo.setMarca("Volkswagen");
        veiculo.setAno(1980);
        veiculo.setDescricao("unico dono");

        when(veiculoService.findByIdVeiculo(1L)).thenReturn(Optional.of(veiculo));

        mockMvc.perform(get("/veiculo/veiculoId/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.veiculo").value("Fusca"))
                .andExpect(jsonPath("$.marca").value("Volkswagen"))
                .andExpect(jsonPath("$.ano").value(1980))
                .andExpect(jsonPath("$.descricao").value("unico dono"));
    }

    @Test
    void buscaTodosVeiculos__should_return_list_veiculos() throws Exception {
        VeiculoEntity veiculo1 = new VeiculoEntity();
        veiculo1.setId(1L);
        veiculo1.setVeiculo("Fusca");
        veiculo1.setMarca("Volkswagen");
        veiculo1.setAno(1980);

        VeiculoEntity veiculo2 = new VeiculoEntity();
        veiculo2.setId(2L);
        veiculo2.setVeiculo("Gol");
        veiculo2.setMarca("Volkswagen");
        veiculo2.setAno(1995);

        List<VeiculoEntity> listaVeiculos = Arrays.asList(veiculo1, veiculo2);

        when(veiculoService.buscaTodosVeiculos()).thenReturn(listaVeiculos);

        mockMvc.perform(get("/veiculo/listarVeiculos")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].veiculo").value("Fusca"))
                .andExpect(jsonPath("$[0].marca").value("Volkswagen"))
                .andExpect(jsonPath("$[0].ano").value(1980))
                .andExpect(jsonPath("$[1].veiculo").value("Gol"))
                .andExpect(jsonPath("$[1].marca").value("Volkswagen"))
                .andExpect(jsonPath("$[1].ano").value(1995));
    }

    @Test
    void buscaTodosVeiculosDisponiveis__should_return_list_veiculos_disponiveis() throws Exception {
        VeiculoEntity veiculo1 = new VeiculoEntity();
        veiculo1.setId(1L);
        veiculo1.setVeiculo("Fusca");
        veiculo1.setMarca("Volkswagen");
        veiculo1.setAno(1980);
        veiculo1.setVendido(false);

        VeiculoEntity veiculo2 = new VeiculoEntity();
        veiculo2.setId(2L);
        veiculo2.setVeiculo("Gol");
        veiculo2.setMarca("Volkswagen");
        veiculo2.setAno(1995);
        veiculo2.setVendido(false);

        List<VeiculoEntity> listaDisponiveis = Arrays.asList(veiculo1, veiculo2);

        when(veiculoService.buscaTodosVeiculosDisponiveis()).thenReturn(listaDisponiveis);

        mockMvc.perform(get("/veiculo/veiculosDisponiveis")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].veiculo").value("Fusca"))
                .andExpect(jsonPath("$[0].vendido").value(false))
                .andExpect(jsonPath("$[1].veiculo").value("Gol"))
                .andExpect(jsonPath("$[1].vendido").value(false));
    }

    @Test
    void countVeiculosDisponiveis__should_return_count_veiculos_disponiveis() throws Exception {
        when(veiculoService.countVeiculosDisponiveis()).thenReturn(5L);

        mockMvc.perform(get("/veiculo/countVeiculosDisponiveis")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("5")); // valida o número retornado
    }

    @Test
    void distribuicaoPorMarcas__should_return_count_veiculos_distribuidos_por_marca() throws Exception {
        DistribuicaoMarcaDTO dto1 = new DistribuicaoMarcaDTO("Volkswagen", 10L);
        DistribuicaoMarcaDTO dto2 = new DistribuicaoMarcaDTO("Fiat", 5L);

        List<DistribuicaoMarcaDTO> distribuicao = Arrays.asList(dto1, dto2);

        when(veiculoService.distribuicaoPorMarca()).thenReturn(distribuicao);

        mockMvc.perform(get("/veiculo/distribuicaoMarcas")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].marca").value("Volkswagen"))
                .andExpect(jsonPath("$[0].total").value(10))
                .andExpect(jsonPath("$[1].marca").value("Fiat"))
                .andExpect(jsonPath("$[1].total").value(5));
    }

    @Test
    void atualizaVeiculos__should_return_veiculo_atualizado() throws Exception {
        AtualizaVeiculoDTO dto = new AtualizaVeiculoDTO(1L, "Kombi", "Volkswagen", 1975, null, false);

        VeiculoEntity veiculoAtualizado = new VeiculoEntity();
        veiculoAtualizado.setId(1L);
        veiculoAtualizado.setVeiculo("Fusca Atualizado");
        veiculoAtualizado.setMarca("Volkswagen");
        veiculoAtualizado.setAno(1981);

        when(veiculoService.atualizaVeiculos(dto)).thenReturn(veiculoAtualizado);

        mockMvc.perform(put("/veiculo/atualizaVeiculos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.veiculo").value("Fusca Atualizado"))
                .andExpect(jsonPath("$.marca").value("Volkswagen"))
                .andExpect(jsonPath("$.ano").value(1981));
    }

    @Test
    void excluirVeiculo__should_return_noCotent() throws Exception {
        Long id = 1L;

        doNothing().when(veiculoService).excluirVeiculo(id);

        mockMvc.perform(delete("/veiculo/excluirVeiculo/{id}", id))
                .andExpect(status().isNoContent());

        verify(veiculoService).excluirVeiculo(id);
    }

}
