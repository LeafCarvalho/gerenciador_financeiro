package dev.leaf_carvalho.gerenciador_financeiro.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InvestimentosDTO {
    private Long usuario_id;
    private String nomeInvestimento;
    private String tipoInvestimento;
    private String recorrenciaInvestimento;
    private Double valorInvestimento;
    private LocalDate dataInvestimentoInicial;
    private String dataInvestimentoFinal;
    private String reciboInvestimento;
}
