package dev.leaf_carvalho.gerenciador_financeiro.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import jakarta.persistence.*;

@Entity
@Table(name = "entradas")
public class Entradas {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_entrada")
    private Long idEntrada;

    @Column(name = "id_usuario", nullable = false)
    private Integer idUsuario;

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
