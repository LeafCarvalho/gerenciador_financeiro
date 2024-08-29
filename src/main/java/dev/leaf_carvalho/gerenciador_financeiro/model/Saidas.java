package dev.leaf_carvalho.gerenciador_financeiro.model;

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
@Table(name = "saidas")
public class Saidas {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long idSaida;

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;
    
    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id")
    private Usuarios usuario;

    @Column(name = "nome_saida", nullable = false)
    private String nomeSaida;
    
    @Column(name = "tipo_saida", nullable = false)
    private String tipoSaida;
    
    @Column(name = "recorrencia_saida", nullable = false)
    private String recorrenciaSaida;
    
    @Column(name = "valor_saida", nullable = false)
    private Double valorSaida;

    @Column(name = "categoria", nullable = false)
    private String categoria;
    
    @Column(name = "data_vencimento", nullable = false)
    private LocalDate dataVencimento;

    @Column(name = "recibo_saida", nullable = false)
    private String reciboSaida;
}
