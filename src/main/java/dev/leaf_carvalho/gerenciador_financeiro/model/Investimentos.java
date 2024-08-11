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
@Table(name = "investimentos")
public class Investimentos {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_investimento")
    private Long idInvestimento;

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;
    
    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
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
