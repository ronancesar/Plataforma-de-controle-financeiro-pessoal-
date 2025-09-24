package br.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import br.com.suafinanca.finance_api.model.Despesa;

public interface DespesaRepository extends JpaRepository<Despesa, Long>{
    
}

