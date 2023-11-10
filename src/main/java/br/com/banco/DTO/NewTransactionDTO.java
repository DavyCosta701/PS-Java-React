package br.com.banco.DTO;

import lombok.*;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class NewTransactionDTO {

    private int numeroConta;
    private BigDecimal valorTransacao;
}
