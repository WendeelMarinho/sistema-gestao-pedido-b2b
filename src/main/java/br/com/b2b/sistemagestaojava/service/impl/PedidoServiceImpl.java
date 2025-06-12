package br.com.b2b.sistemagestaojava.service.impl;

import br.com.b2b.sistemagestaojava.controller.dto.PedidoRequestDTO;
import br.com.b2b.sistemagestaojava.controller.dto.PedidoResponseDTO;
import br.com.b2b.sistemagestaojava.exception.CreditException;
import br.com.b2b.sistemagestaojava.exception.InvalidStatusUpdateException;
import br.com.b2b.sistemagestaojava.model.*;
import br.com.b2b.sistemagestaojava.repository.ParceiroRepository;
import br.com.b2b.sistemagestaojava.repository.PedidoRepository;
import br.com.b2b.sistemagestaojava.service.PedidoService;
import br.com.b2b.sistemagestaojava.service.notification.NotificationService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class PedidoServiceImpl implements PedidoService {

    private final PedidoRepository pedidoRepository;
    private final ParceiroRepository parceiroRepository;
    private final NotificationService notificationService;

    public PedidoServiceImpl(PedidoRepository pedidoRepository, ParceiroRepository parceiroRepository, NotificationService notificationService) {
        this.pedidoRepository = pedidoRepository;
        this.parceiroRepository = parceiroRepository;
        this.notificationService = notificationService;
    }

    @Override
    @Transactional
    public PedidoResponseDTO criarPedido(PedidoRequestDTO dto) {
        Parceiro parceiro = parceiroRepository.findById(dto.idParceiro())
                .orElseThrow(() -> new EntityNotFoundException("Parceiro não encontrado com ID: " + dto.idParceiro()));

        Pedido pedido = new Pedido();
        pedido.setParceiro(parceiro);
        pedido.setStatus(StatusPedido.PENDENTE);

        dto.itens().forEach(itemDto -> {
            ItemPedido item = new ItemPedido();
            item.setProduto(itemDto.produto());
            item.setQuantidade(itemDto.quantidade());
            item.setPrecoUnitario(itemDto.precoUnitario());
            pedido.adicionarItem(item);
        });

        pedido.calcularValorTotal();
        Pedido pedidoSalvo = pedidoRepository.save(pedido);
        notificationService.notificar(pedidoSalvo, "Pedido criado com sucesso.");
        return PedidoResponseDTO.fromEntity(pedidoSalvo);
    }
    
    @Override
    @Transactional
    public PedidoResponseDTO atualizarStatus(UUID id, StatusPedido novoStatus) {
        Pedido pedido = pedidoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Pedido não encontrado com ID: " + id));

        if (novoStatus == StatusPedido.APROVADO) {
            return aprovarPedido(pedido);
        }

        if (novoStatus == StatusPedido.CANCELADO) {
            return cancelarPedido(id);
        }

        pedido.setStatus(novoStatus);
        Pedido pedidoAtualizado = pedidoRepository.save(pedido);
        notificationService.notificar(pedidoAtualizado, "Status atualizado para " + novoStatus);
        return PedidoResponseDTO.fromEntity(pedidoAtualizado);
    }

    @Override
    @Transactional
    public PedidoResponseDTO cancelarPedido(UUID id) {
        Pedido pedido = pedidoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Pedido não encontrado com ID: " + id));

        if (pedido.getStatus() == StatusPedido.APROVADO) {
             Parceiro parceiro = pedido.getParceiro();
             BigDecimal valorRestaurado = parceiro.getCreditoDisponivel().add(pedido.getValorTotal());
             parceiro.setCreditoDisponivel(valorRestaurado);
             parceiroRepository.save(parceiro);
        }
        
        pedido.setStatus(StatusPedido.CANCELADO);
        Pedido pedidoCancelado = pedidoRepository.save(pedido);
        notificationService.notificar(pedidoCancelado, "Pedido cancelado.");
        return PedidoResponseDTO.fromEntity(pedidoCancelado);
    }

    private PedidoResponseDTO aprovarPedido(Pedido pedido) {
        if (pedido.getStatus() != StatusPedido.PENDENTE) {
            throw new InvalidStatusUpdateException("Apenas pedidos PENDENTES podem ser aprovados.");
        }

        Parceiro parceiro = pedido.getParceiro();
        BigDecimal valorPedido = pedido.getValorTotal();

        if (parceiro.getCreditoDisponivel().compareTo(valorPedido) < 0) {
            throw new CreditException("Crédito insuficiente para aprovar o pedido.");
        }

        parceiro.setCreditoDisponivel(parceiro.getCreditoDisponivel().subtract(valorPedido));
        parceiroRepository.save(parceiro);

        pedido.setStatus(StatusPedido.APROVADO);
        Pedido pedidoAprovado = pedidoRepository.save(pedido);
        notificationService.notificar(pedidoAprovado, "Pedido aprovado e crédito debitado.");
        return PedidoResponseDTO.fromEntity(pedidoAprovado);
    }

    @Override
    public PedidoResponseDTO buscarPorId(UUID id) {
        Pedido pedido = pedidoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Pedido não encontrado com ID: " + id));
        return PedidoResponseDTO.fromEntity(pedido);
    }

    @Override
    public List<PedidoResponseDTO> listarPorStatus(StatusPedido status) {
        return pedidoRepository.findByStatus(status).stream()
                .map(PedidoResponseDTO::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public List<PedidoResponseDTO> listarPorPeriodo(LocalDate dataInicio, LocalDate dataFim) {
        return pedidoRepository.findByDataCriacaoBetween(dataInicio.atStartOfDay(), dataFim.atTime(LocalTime.MAX))
                .stream()
                .map(PedidoResponseDTO::fromEntity)
                .collect(Collectors.toList());
    }
}