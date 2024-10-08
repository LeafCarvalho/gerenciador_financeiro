package dev.leaf_carvalho.gerenciador_financeiro.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InvestimentosDTO {
	private Long id;
    private Long usuario_id;
    private String nomeInvestimento;
    private String tipoInvestimento;
    private BigDecimal valorInvestimento;
    private String categoria;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate dataInvestimentoInicial;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate dataInvestimentoFinal;
    private String reciboInvestimento;
    private Long idRecorrencia;
    private String tipoRecorrencia;
}
