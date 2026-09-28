package br.com.fatec.ninjas.service;

import br.com.fatec.ninjas.model.Cla;
import br.com.fatec.ninjas.repository.ClaRepository;
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
public class ClaServiceTest {

    @Mock
    private ClaRepository claRepository;

    @InjectMocks
    private ClaService claService;

    private Cla cla;

    @BeforeEach
    void setUp() {
        cla = new Cla();
        cla.setId(1L);
        cla.setNome("Uchiha");
        cla.setDescricao("Especialistas em Katon e Sharingan");
        cla.setHabilidade("Sharingan");
    }

    @Test
    void testCadastrarCla() {
        when(claRepository.save(cla)).thenReturn(cla);

        Cla resultado = claService.cadastrarCla(cla);

        assertNotNull(resultado);
        assertEquals("Uchiha", resultado.getNome());
        verify(claRepository, times(1)).save(cla);
    }

    @Test
    void testListarClas() {
        when(claRepository.findAll()).thenReturn(Arrays.asList(cla));

        List<Cla> resultado = claService.listarClas();

        assertFalse(resultado.isEmpty());
        assertEquals(1, resultado.size());
        assertEquals("Uchiha", resultado.get(0).getNome());
        verify(claRepository, times(1)).findAll();
    }

    @Test
    void testPesquisarClaPorId() {
        when(claRepository.findById(1L)).thenReturn(Optional.of(cla));

        Optional<Cla> resultado = claService.pesquisarCla(1L);

        assertTrue(resultado.isPresent());
        assertEquals("Uchiha", resultado.get().getNome());
        verify(claRepository, times(1)).findById(1L);
    }

    @Test
    void testPesquisarClaPorNome() {
        when(claRepository.findByNome("Uchiha")).thenReturn(cla);

        Cla resultado = claService.pesquisarClaPorNome("Uchiha");

        assertNotNull(resultado);
        assertEquals("Uchiha", resultado.getNome());
        verify(claRepository, times(1)).findByNome("Uchiha");
    }

    @Test
    void testPesquisarClaPorParteDaDescricao() {
        when(claRepository.findByDescricaoContainingIgnoreCase("Katon")).thenReturn(Arrays.asList(cla));

        List<Cla> resultado = claService.pesquisarClaPorParteDaDescricao("Katon");

        assertFalse(resultado.isEmpty());
        assertEquals(1, resultado.size());
        verify(claRepository, times(1)).findByDescricaoContainingIgnoreCase("Katon");
    }

    @Test
    void testAtualizarClaExistente() {
        when(claRepository.findById(1L)).thenReturn(Optional.of(cla));

        Cla atualizado = new Cla();
        atualizado.setNome("Uchiha Atualizado");
        atualizado.setDescricao("Nova descricao");
        atualizado.setHabilidade("Mangekyo");

        when(claRepository.save(any(Cla.class))).thenReturn(cla);

        Cla resultado = claService.atualizarCla(1L, atualizado);

        assertNotNull(resultado);
        assertEquals("Uchiha Atualizado", resultado.getNome());
        verify(claRepository, times(1)).save(any(Cla.class));
    }

    @Test
    void testAtualizarClaInexistente() {
        when(claRepository.findById(99L)).thenReturn(Optional.empty());

        Cla resultado = claService.atualizarCla(99L, cla);

        assertNull(resultado);
        verify(claRepository, never()).save(any(Cla.class));
    }

    @Test
    void testDeletarCla() {
        doNothing().when(claRepository).deleteById(1L);

        claService.deletarCla(1L);

        verify(claRepository, times(1)).deleteById(1L);
    }
}
