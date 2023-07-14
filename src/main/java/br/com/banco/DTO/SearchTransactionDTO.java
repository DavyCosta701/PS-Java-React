package br.com.banco.DTO;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class SearchTransactionDTO {

    private String dataInicial;
    private String dataFinal;
    private String nomeOperador;

}
