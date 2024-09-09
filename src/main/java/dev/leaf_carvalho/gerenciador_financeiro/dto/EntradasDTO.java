package dev.leaf_carvalho.gerenciador_financeiro.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EntradasDTO {
    private Long usuario_id;
    private BigDecimal salario;
    private String nomeEntrada;
    private String tipoEntrada;
    private Double valorEntrada;
    private LocalDate dataEntrada;
    private String reciboEntrada;
    private Long idRecorrencia;
    private String tipoRecorrencia;
}

