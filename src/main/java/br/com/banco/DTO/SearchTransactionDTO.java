package br.com.banco.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SearchTransactionDTO {

    private String dataInicial;
    private String dataFinal;
    private String nomeOperador;

}
