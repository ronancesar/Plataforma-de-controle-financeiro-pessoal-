package br.com.suafinanca.finance_api.model;
import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDate;


@MappedSuperclass //Esta classe não sera uma tabela, mas suas crias serão
@Data
public class Transacao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; 

    private String descricao;
    private BigDecimal valor; 
    private LocalDate data; 
    private String categoria; 
    
    /*Na fase 2, adicionaremos a relação com o usuario 
    @MannyToOne 
    @JoinColumm(name = "usuario_id")
    private Usuario usuario */  
    

    
}
