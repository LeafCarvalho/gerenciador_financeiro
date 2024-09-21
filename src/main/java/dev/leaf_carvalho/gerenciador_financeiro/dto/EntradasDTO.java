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
public class EntradasDTO {
	private Long id;
    private Long usuario_id;
    private String nomeEntrada;
    private String tipoEntrada;
    private BigDecimal valorEntrada;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate dataEntrada;
    private String reciboEntrada;
    private Long idRecorrencia;
    private String tipoRecorrencia;
}

