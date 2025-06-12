package br.com.b2b.sistemagestaojava.controller;

import br.com.b2b.sistemagestaojava.controller.dto.PedidoRequestDTO;
import br.com.b2b.sistemagestaojava.controller.dto.PedidoResponseDTO;
import br.com.b2b.sistemagestaojava.model.StatusPedido;
import br.com.b2b.sistemagestaojava.service.PedidoService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import java.net.URI;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    private final PedidoService pedidoService;

    public PedidoController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    @PostMapping
    @Operation(summary = "Cria um novo pedido com status PENDENTE")
    public ResponseEntity<PedidoResponseDTO> criarPedido(@RequestBody @Valid PedidoRequestDTO dto, UriComponentsBuilder uriBuilder) {
        PedidoResponseDTO pedidoCriado = pedidoService.criarPedido(dto);
        URI uri = uriBuilder.path("/pedidos/{id}").buildAndExpand(pedidoCriado.id()).toUri();
        return ResponseEntity.created(uri).body(pedidoCriado);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Busca um pedido pelo seu ID único")
    public ResponseEntity<PedidoResponseDTO> buscarPorId(@PathVariable UUID id) {
        return ResponseEntity.ok(pedidoService.buscarPorId(id));
    }

    @GetMapping
    @Operation(summary = "Lista pedidos por status ou por período (yyyy-MM-dd)")
    public ResponseEntity<List<PedidoResponseDTO>> listarPedidos(
            @RequestParam(required = false) StatusPedido status,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataInicio,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataFim
    ) {
        if (status != null) {
            return ResponseEntity.ok(pedidoService.listarPorStatus(status));
        }
        if (dataInicio != null && dataFim != null) {
            return ResponseEntity.ok(pedidoService.listarPorPeriodo(dataInicio, dataFim));
        }
        return ResponseEntity.badRequest().build();
    }

    @PatchMapping("/{id}/status")
    @Operation(summary = "Atualiza o status de um pedido (ex: APROVADO)")
    public ResponseEntity<PedidoResponseDTO> atualizarStatus(
            @PathVariable UUID id,
            @RequestBody StatusPedido novoStatus
    ) {
        return ResponseEntity.ok(pedidoService.atualizarStatus(id, novoStatus));
    }

    @PostMapping("/{id}/cancelar")
    @Operation(summary = "Cancela um pedido")
    public ResponseEntity<PedidoResponseDTO> cancelarPedido(@PathVariable UUID id) {
        return ResponseEntity.ok(pedidoService.cancelarPedido(id));
    }
}