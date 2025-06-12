package br.com.b2b.sistemagestaojava.repository;

import br.com.b2b.sistemagestaojava.model.Parceiro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParceiroRepository extends JpaRepository<Parceiro, Long> {
}