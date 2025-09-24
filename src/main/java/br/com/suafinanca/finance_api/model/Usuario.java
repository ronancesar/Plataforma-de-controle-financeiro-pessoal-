package br.com.suafinanca.finance_api.model;
import jakarta.persistence.*;
import lombok.Data;


@Entity
@Table (name = "usuarios")
@Data // Lombok 

public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    private String nome;
    
    @Column (unique = true)
    private String email; 

    private String senha;
    
}
