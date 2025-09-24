package br.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import br.com.suafinanca.finance_api.model.Receita;

public interface ReceitaRepository extends JpaRepository<Receita, Long> {
    
}
