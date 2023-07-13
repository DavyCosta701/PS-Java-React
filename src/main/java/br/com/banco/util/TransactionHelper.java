package br.com.banco.util;

import br.com.banco.domain.entity.TransactionEntity;
import br.com.banco.domain.repository.TransactionRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.experimental.UtilityClass;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Component
public class TransactionHelper {

    private final TransactionRepository transactionRepository;

    public  BigDecimal calculateSaldoPeriodo(List<TransactionEntity> transactionEntities) {
        List<BigDecimal> valores = new ArrayList<>();
        transactionEntities.forEach(transactionEntity -> valores.add(transactionEntity.getValorTransferencia()));
        return valores.stream().reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public  BigDecimal calculateSaldoTotal() {
        List<TransactionEntity> listTransaction = transactionRepository.findAll();
        List<BigDecimal> valores = new ArrayList<>();
        listTransaction.forEach(transactionEntity -> valores.add(transactionEntity.getValorTransferencia()));
        return valores.stream().reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
