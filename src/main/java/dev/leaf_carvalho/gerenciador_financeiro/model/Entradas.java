package dev.leaf_carvalho.gerenciador_financeiro.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "entradas")
public class Entradas {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long idEntrada;
    
    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;
    
    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuarios usuario;

    @Column(name = "salario", nullable = false)
    private BigDecimal salario;

    @Column(name = "nome_entrada", nullable = false)
    private String nomeEntrada;
    
    @Column(name = "tipo_entrada", nullable = false)
    private String tipoEntrada;
    
    @Column(name = "recorrencia_entrada", nullable = false)
    private String recorrenciaEntrada;
    
    @Column(name = "valor_entrada", nullable = false)
    private Double valorEntrada;

    @Column(name = "data_entrada", nullable = false)
    private LocalDate dataEntrada;

    @Column(name = "recibo_entrada", nullable = false)
    private String reciboEntrada;
}
