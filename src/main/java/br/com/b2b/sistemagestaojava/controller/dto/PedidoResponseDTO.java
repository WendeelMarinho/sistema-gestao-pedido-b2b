package br.com.b2b.sistemagestaojava.controller.dto;

import br.com.b2b.sistemagestaojava.model.Pedido;
import br.com.b2b.sistemagestaojava.model.StatusPedido;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public record PedidoResponseDTO(
    UUID id,
    Long idParceiro,
    List<ItemPedidoRequestDTO> itens,
    BigDecimal valorTotal,
    StatusPedido status,
    LocalDateTime dataCriacao,
    LocalDateTime dataUltimaAtualizacao
) {
    public static PedidoResponseDTO fromEntity(Pedido pedido) {
        List<ItemPedidoRequestDTO> itemDTOs = pedido.getItens().stream()
                .map(item -> new ItemPedidoRequestDTO(item.getProduto(), item.getQuantidade(), item.getPrecoUnitario()))
                .collect(Collectors.toList());

        return new PedidoResponseDTO(
                pedido.getId(),
                pedido.getParceiro().getId(),
                itemDTOs,
                pedido.getValorTotal(),
                pedido.getStatus(),
                pedido.getDataCriacao(),
                pedido.getDataUltimaAtualizacao()
        );
    }
}