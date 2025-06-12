package br.com.b2b.sistemagestaojava.repository;

import br.com.b2b.sistemagestaojava.model.Pedido;
import br.com.b2b.sistemagestaojava.model.StatusPedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, UUID> {
    List<Pedido> findByStatus(StatusPedido status);
    List<Pedido> findByDataCriacaoBetween(LocalDateTime dataInicio, LocalDateTime dataFim);
    List<Pedido> findByParceiroId(Long parceiroId);
}