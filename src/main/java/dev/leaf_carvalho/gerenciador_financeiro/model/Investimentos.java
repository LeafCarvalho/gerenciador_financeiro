package dev.leaf_carvalho.gerenciador_financeiro.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "investimentos")
public class Investimentos {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long idInvestimento;

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;
    
    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id")
    private Usuarios usuario;

    @Column(name = "nome_investimento", nullable = false)
    private String nomeInvestimento;
    
    @Column(name = "tipo_investimento", nullable = false)
    private String tipoInvestimento;
    
    @Column(name = "recorrencia_investimento", nullable = false)
    private String recorrenciaInvestimento;
    
    @Column(name = "valor_investimento", nullable = false)
    private BigDecimal valorInvestimento;

    @Column(name = "data_investimento_inicial", nullable = false)
    private LocalDate dataInvestimentoInicial;
    
    @Column(name = "data_investimento_final", nullable = false)
    private LocalDate dataInvestimentoFinal;

    @Column(name = "recibo_investimento", nullable = false)
    private String reciboInvestimento;
}
