package br.com.b2b.sistemagestaojava.service;

import br.com.b2b.sistemagestaojava.controller.dto.PedidoRequestDTO;
import br.com.b2b.sistemagestaojava.controller.dto.PedidoResponseDTO;
import br.com.b2b.sistemagestaojava.model.StatusPedido;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface PedidoService {
    PedidoResponseDTO criarPedido(PedidoRequestDTO dto);
    PedidoResponseDTO buscarPorId(UUID id);
    List<PedidoResponseDTO> listarPorStatus(StatusPedido status);
    List<PedidoResponseDTO> listarPorPeriodo(LocalDate dataInicio, LocalDate dataFim);
    PedidoResponseDTO atualizarStatus(UUID id, StatusPedido novoStatus);
    PedidoResponseDTO cancelarPedido(UUID id);
}