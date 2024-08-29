package dev.leaf_carvalho.gerenciador_financeiro.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EntradasDTO {
    private Long usuario_id;
    private BigDecimal salario;
    private String nomeEntrada;
    private String tipoEntrada;
    private String recorrenciaEntrada;
    private Double valorEntrada;
    private LocalDate dataEntrada;
    private String reciboEntrada;
}