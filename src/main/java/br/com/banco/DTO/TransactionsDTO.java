package br.com.banco.DTO;

import br.com.banco.domain.entity.TransactionEntity;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Data
@Getter
@Setter
public class TransactionsDTO {

    List<TransactionEntity> transactions;
    private BigDecimal saldoTotal;
    private BigDecimal saldoPeriodo;


}
