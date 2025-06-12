package br.com.b2b.sistemagestaojava.service.impl;

import br.com.b2b.sistemagestaojava.controller.dto.ItemPedidoRequestDTO;
import br.com.b2b.sistemagestaojava.controller.dto.PedidoRequestDTO;
import br.com.b2b.sistemagestaojava.exception.CreditException;
import br.com.b2b.sistemagestaojava.model.Parceiro;
import br.com.b2b.sistemagestaojava.model.Pedido;
import br.com.b2b.sistemagestaojava.model.StatusPedido;
import br.com.b2b.sistemagestaojava.repository.ParceiroRepository;
import br.com.b2b.sistemagestaojava.repository.PedidoRepository;
import br.com.b2b.sistemagestaojava.service.notification.NotificationService;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PedidoServiceImplTest {

    @Mock
    private PedidoRepository pedidoRepository;
    @Mock
    private ParceiroRepository parceiroRepository;
    @Mock
    private NotificationService notificationService;
    @InjectMocks
    private PedidoServiceImpl pedidoService;

    private Parceiro parceiro;
    private PedidoRequestDTO pedidoRequestDTO;

    @BeforeEach
    void setUp() {
        parceiro = new Parceiro();
        parceiro.setId(1L);
        parceiro.setNome("Parceiro Teste");
        parceiro.setLimiteCredito(new BigDecimal("1000.00"));
        parceiro.setCreditoDisponivel(new BigDecimal("1000.00"));

        ItemPedidoRequestDTO itemDTO = new ItemPedidoRequestDTO("Produto A", 2, new BigDecimal("100.00"));
        pedidoRequestDTO = new PedidoRequestDTO(1L, List.of(itemDTO));
    }

    @Test
    void criarPedido_deveFuncionarCorretamente() {
        when(parceiroRepository.findById(1L)).thenReturn(Optional.of(parceiro));
        when(pedidoRepository.save(any(Pedido.class))).thenAnswer(invocation -> invocation.getArgument(0));

        pedidoService.criarPedido(pedidoRequestDTO);

        verify(pedidoRepository, times(1)).save(any(Pedido.class));
        verify(notificationService, times(1)).notificar(any(Pedido.class), anyString());
    }

    @Test
    void criarPedido_deveLancarExcecao_quandoParceiroNaoEncontrado() {
        when(parceiroRepository.findById(1L)).thenReturn(Optional.empty());
        assertThrows(EntityNotFoundException.class, () -> pedidoService.criarPedido(pedidoRequestDTO));
    }
    
    @Test
    void aprovarPedido_deveDebitarCredito_quandoHaCreditoSuficiente() {
        Pedido pedidoPendente = new Pedido();
        pedidoPendente.setParceiro(parceiro);
        pedidoPendente.setStatus(StatusPedido.PENDENTE);
        pedidoPendente.setValorTotal(new BigDecimal("200.00"));

        when(pedidoRepository.findById(any())).thenReturn(Optional.of(pedidoPendente));
        when(pedidoRepository.save(any(Pedido.class))).thenReturn(pedidoPendente);
        
        pedidoService.atualizarStatus(pedidoPendente.getId(), StatusPedido.APROVADO);
        
        assertEquals(new BigDecimal("800.00"), parceiro.getCreditoDisponivel());
        verify(parceiroRepository, times(1)).save(parceiro);
    }
    
    @Test
    void aprovarPedido_deveLancarExcecao_quandoCreditoInsuficiente() {
        parceiro.setCreditoDisponivel(new BigDecimal("100.00"));
        Pedido pedidoPendente = new Pedido();
        pedidoPendente.setParceiro(parceiro);
        pedidoPendente.setStatus(StatusPedido.PENDENTE);
        pedidoPendente.setValorTotal(new BigDecimal("200.00"));

        when(pedidoRepository.findById(any())).thenReturn(Optional.of(pedidoPendente));
        
        assertThrows(CreditException.class, () -> {
            pedidoService.atualizarStatus(pedidoPendente.getId(), StatusPedido.APROVADO);
        });
    }
}