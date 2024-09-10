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
@Table(name = "entradas")
public class Entradas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id", nullable = false)
    @JsonBackReference
    private Usuarios usuario;

    @Column(name = "salario", nullable = false)
    private BigDecimal salario;

    @Column(name = "nome_entrada", nullable = false)
    private String nomeEntrada;

    @Column(name = "tipo_entrada", nullable = false)
    private String tipoEntrada;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_recorrencia")
    private Tipo_Recorrencia tipoRecorrencia;

    @Column(name = "valor_entrada", nullable = false)
    private Double valorEntrada;

    @Column(name = "data_entrada", nullable = false)
    private LocalDate dataEntrada;

    @Column(name = "recibo_entrada", nullable = false)
    private String reciboEntrada;

    public void setUsuario(Usuarios usuario) {
        this.usuario = usuario;
    }
    
    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    public void preUpdate() {
        this.updatedAt = LocalDateTime.now();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Entradas entrada = (Entradas) o;
        return Objects.equals(id, entrada.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
