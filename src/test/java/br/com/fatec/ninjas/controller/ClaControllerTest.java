package br.com.fatec.ninjas.controller;

import br.com.fatec.ninjas.model.Cla;
import br.com.fatec.ninjas.service.ClaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ClaControllerTest {

    @Mock
    private ClaService claService;

    @InjectMocks
    private ClaController claController;

    private Cla cla;

    @BeforeEach
    void setUp() {
        cla = new Cla();
        cla.setId(1L);
        cla.setNome("Hyuga");
        cla.setDescricao("Byakugan e punho suave");
        cla.setHabilidade("Byakugan");
    }

    @Test
    void testCadastrarCla() {
        when(claService.cadastrarCla(cla)).thenReturn(cla);

        Cla res = claController.cadastrarCla(cla);

        assertNotNull(res);
        assertEquals("Hyuga", res.getNome());
        verify(claService).cadastrarCla(cla);
    }

    @Test
    void testListarClas() {
        when(claService.listarClas()).thenReturn(Arrays.asList(cla));

        List<Cla> res = claController.listarClas();

        assertEquals(1, res.size());
        verify(claService).listarClas();
    }

    @Test
    void testPesquisarClaPorId() {
        when(claService.pesquisarCla(1L)).thenReturn(Optional.of(cla));

        Optional<Cla> res = claController.pesquisarCla(1L);

        assertTrue(res.isPresent());
        assertEquals("Hyuga", res.get().getNome());
        verify(claService).pesquisarCla(1L);
    }

    @Test
    void testPesquisarClaPorNome() {
        when(claService.pesquisarClaPorNome("Hyuga")).thenReturn(cla);

        Cla res = claController.pesquisarClaPorNome("Hyuga");

        assertNotNull(res);
        assertEquals("Hyuga", res.getNome());
        verify(claService).pesquisarClaPorNome("Hyuga");
    }

    @Test
    void testPesquisarClaPorDescricao() {
        when(claService.pesquisarClaPorParteDaDescricao("Byakugan")).thenReturn(Arrays.asList(cla));

        List<Cla> res = claController.pesquisarClaPorDescricao("Byakugan");

        assertEquals(1, res.size());
        verify(claService).pesquisarClaPorParteDaDescricao("Byakugan");
    }

    @Test
    void testAtualizarCla() {
        when(claService.atualizarCla(1L, cla)).thenReturn(cla);

        Cla res = claController.atualizarCla(1L, cla);

        assertNotNull(res);
        assertEquals("Hyuga", res.getNome());
        verify(claService).atualizarCla(1L, cla);
    }

    @Test
    void testDeletarCla() {
        doNothing().when(claService).deletarCla(1L);

        claController.deletarCla(1L);

        verify(claService).deletarCla(1L);
    }
}
