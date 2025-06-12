package br.com.b2b.sistemagestaojava.service.notification;

import br.com.b2b.sistemagestaojava.model.Pedido;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {
    public void notificar(Pedido pedido, String mensagem) {
        System.out.printf(
            "SIMULANDO NOTIFICAÇÃO: Pedido ID %s, Parceiro ID %d. Mensagem: %s. Novo Status: %s%n",
            pedido.getId(),
            pedido.getParceiro().getId(),
            mensagem,
            pedido.getStatus()
        );
    }
}