package dev.leaf_carvalho.gerenciador_financeiro.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

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
    private Long id;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuarios usuario;

    @Column(name = "nome_investimento", nullable = false)
    private String nomeInvestimento;

    @Column(name = "tipo_investimento", nullable = false)
    private String tipoInvestimento;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_recorrencia", nullable = false)
    private Tipo_Recorrencia tipoRecorrencia;
    
    @Column(name = "categoria", nullable = false)
    private String categoria;

    @Column(name = "valor_investimento", nullable = false)
    private BigDecimal valorInvestimento;

    @Column(name = "data_investimento_inicial", nullable = false)
    private LocalDate dataInvestimentoInicial;

    @Column(name = "data_investimento_final", nullable = false)
    private LocalDate dataInvestimentoFinal;

    @Column(name = "recibo_investimento", nullable = false)
    private String reciboInvestimento;

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Investimentos other = (Investimentos) obj;
        return Objects.equals(id, other.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Investimentos [id=" + id + "]";
    }
}
