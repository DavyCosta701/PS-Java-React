package br.com.banco.service;

import br.com.banco.DTO.SearchTransactionDTO;
import br.com.banco.DTO.TransactionsDTO;
import br.com.banco.domain.entity.TransactionEntity;
import br.com.banco.domain.repository.TransactionRepository;
import br.com.banco.exception.DateException;
import br.com.banco.exception.TransacaoException;
import br.com.banco.helper.TransactionHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {

     final TransactionRepository transactionRepository;
     final TransactionHelper transactionHelper;

    @Override
    public TransactionsDTO searchFilter(SearchTransactionDTO searchTransactionDTO) {

        if (!Objects.equals(searchTransactionDTO.getDataInicial(), "") &&
                !Objects.equals(searchTransactionDTO.getDataFinal(), "") &&
                Objects.equals(searchTransactionDTO.getNomeOperador(), "")) {
            LocalDate dataInicio = LocalDate.parse(searchTransactionDTO.getDataInicial(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            LocalDate dataFim = LocalDate.parse(searchTransactionDTO.getDataFinal(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));

            return findByDate(dataInicio,dataFim);

        }else if (!Objects.equals(searchTransactionDTO.getNomeOperador(), "") &&
                !Objects.equals(searchTransactionDTO.getDataInicial(), "") &&
                !Objects.equals(searchTransactionDTO.getDataFinal(), "")
                ) {
            LocalDate dataInicio = LocalDate.parse(searchTransactionDTO.getDataInicial(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            LocalDate dataFim = LocalDate.parse(searchTransactionDTO.getDataFinal(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));

            return findByDateName(dataInicio, dataFim, searchTransactionDTO.getNomeOperador());
        } else if (!Objects.equals(searchTransactionDTO.getNomeOperador(), "") &&
                Objects.equals(searchTransactionDTO.getDataInicial(), "") &&
                Objects.equals(searchTransactionDTO.getDataFinal(), "")
        ) {
            return findByName(searchTransactionDTO.getNomeOperador());
        }
        return findAll();
    }

    @Override
    public TransactionsDTO findByDate(LocalDate dataInicial, LocalDate dataFinal) {
        if (dataInicial.isAfter(dataFinal)){
            throw new DateException("Data de inicio não pode ser depois de data final.");
        }
        List<TransactionEntity> listTransaction = transactionRepository.findByDataTransferenciaBetween(
                dataInicial, dataFinal
        );
        return getTransactionsDTO(listTransaction);
    }
    @Override
    public TransactionsDTO findByName(String nome) {
        List<TransactionEntity> listTransaction = transactionRepository.findByOperadorLike(nome);
        return getTransactionsDTO(listTransaction);
    }


    @Override
    public TransactionsDTO findByDateName(LocalDate dataInicial, LocalDate dataFinal, String operador) {
        if (dataInicial.isAfter(dataFinal)){
            throw new DateException("Data de inicio não pode ser depois de data final.");
        }
        List<TransactionEntity> listTransaction = transactionRepository.findByDataTransferenciaBetweenAndOperadorLike(
                dataInicial, dataFinal, operador
        );
        return getTransactionsDTO(listTransaction);
    }

    @Override
    public TransactionsDTO findAll() {
        List<TransactionEntity> listTransaction = transactionRepository.findAll();
        return getTransactionsDTO(listTransaction);
    }

    private TransactionsDTO getTransactionsDTO(List<TransactionEntity> listTransaction) {
        if (listTransaction.isEmpty()){
            throw new TransacaoException("Sem transações disponíveis");
        }

        BigDecimal saldoTotal = transactionHelper.calculateSaldoTotal();
        BigDecimal saldoPeriodo = transactionHelper.calculateSaldoPeriodo(listTransaction);

        return TransactionsDTO
                .builder()
                .transactions(listTransaction)
                .saldoPeriodo(saldoPeriodo)
                .saldoTotal(saldoTotal)
                .build();
    }

    
}
