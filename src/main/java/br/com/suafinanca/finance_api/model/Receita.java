package br.com.suafinanca.finance_api.model;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table (name = "receitas")
@Data 
@EqualsAndHashCode(callSuper = true)

public class Receita extends Transacao {
    //pode ter campos especificos de receita depois
    
}
