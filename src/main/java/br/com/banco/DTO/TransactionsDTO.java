package br.com.banco.DTO;

import br.com.banco.domain.entity.TransactionEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class TransactionsDTO {

    List<TransactionEntity> transactions;
    private BigDecimal saldoTotal;
    private BigDecimal saldoPeriodo;


}
