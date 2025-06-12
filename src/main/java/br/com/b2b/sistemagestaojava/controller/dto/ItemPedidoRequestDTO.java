package br.com.b2b.sistemagestaojava.controller.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.math.BigDecimal;

public record ItemPedidoRequestDTO(
    @NotBlank String produto,
    @Positive int quantidade,
    @NotNull @Positive BigDecimal precoUnitario
) {}