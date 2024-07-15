package dev.leaf_carvalho.gerenciador_financeiro.model;

import java.time.LocalDate;
import jakarta.persistence.*;

@Entity
@Table(name = "saidas")
public class Saidas {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_saida")
    private Long idSaida;

    @Column(name = "id_usuario", nullable = false)
    private Integer idUsuario;

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
