package br.com.b2b.sistemagestaojava.model;

import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal;

@Data
@Entity
@Table(name = "parceiros")
public class Parceiro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String nome;

    @Column(nullable = false)
    private BigDecimal limiteCredito;

    @Column(nullable = false)
    private BigDecimal creditoDisponivel;

    @Version
    private Long version;
}