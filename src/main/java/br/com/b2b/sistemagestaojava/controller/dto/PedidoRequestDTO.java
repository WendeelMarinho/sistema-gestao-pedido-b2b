package br.com.b2b.sistemagestaojava.controller.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import java.util.List;

public record PedidoRequestDTO(
    @NotNull Long idParceiro,
    @NotEmpty @Valid List<ItemPedidoRequestDTO> itens
) {}