package dev.leaf_carvalho.gerenciador_financeiro.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SaidasDTO {
    private Long usuario_id;
    private String nomeSaida;
    private String tipoSaida;
    private Double valorSaida;
    private String categoria;
    private LocalDate dataVencimento;
    private String reciboSaida;
    private Long idRecorrencia;
    private String tipoRecorrencia;
}
