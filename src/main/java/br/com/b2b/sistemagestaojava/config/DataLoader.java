package br.com.b2b.sistemagestaojava.config;

import br.com.b2b.sistemagestaojava.model.Parceiro;
import br.com.b2b.sistemagestaojava.repository.ParceiroRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import java.math.BigDecimal;

@Component
public class DataLoader implements CommandLineRunner {

    private final ParceiroRepository parceiroRepository;

    public DataLoader(ParceiroRepository parceiroRepository) {
        this.parceiroRepository = parceiroRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (parceiroRepository.count() == 0) {
            System.out.println("Populando o banco de dados com um parceiro de teste...");
            Parceiro parceiro = new Parceiro();
            parceiro.setNome("Parceiro Teste S.A.");
            parceiro.setLimiteCredito(new BigDecimal("10000.00"));
            parceiro.setCreditoDisponivel(new BigDecimal("10000.00"));
            parceiroRepository.save(parceiro);
            System.out.println("Parceiro de teste criado com ID: " + parceiro.getId());
        }
    }
}