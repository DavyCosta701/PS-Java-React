package br.com.banco.service;

import br.com.banco.DTO.SearchTransactionDTO;
import br.com.banco.DTO.TransactionsDTO;
import br.com.banco.domain.entity.TransactionEntity;
import br.com.banco.domain.repository.TransactionRepository;
import br.com.banco.exception.DateException;
import br.com.banco.exception.TransactionException;
import br.com.banco.util.TransactionHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;
    private final TransactionHelper transactionHelper;

    @Override
    public TransactionsDTO searchFilter(SearchTransactionDTO searchTransactionDTO) throws Exception {
        if (searchTransactionDTO.getDataInicial() != null &&
                searchTransactionDTO.getDataFinal()!= null &&
                searchTransactionDTO.getNomeOperador() == null) {
            LocalDate dataInicio = LocalDate.parse(searchTransactionDTO.getDataInicial(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            LocalDate dataFim = LocalDate.parse(searchTransactionDTO.getDataFinal(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));

            if (dataInicio.isAfter(dataFim)){
                throw new DateException("Data de inicio não pode ser depois de data final.") {
                };
            }
            return findByDate(dataInicio,dataFim);

        }else if (searchTransactionDTO.getNomeOperador() != null &&
                searchTransactionDTO.getDataInicial() != null &&
                 searchTransactionDTO.getDataFinal() != null
                ) {
            LocalDate dataInicio = LocalDate.parse(searchTransactionDTO.getDataInicial(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            LocalDate dataFim = LocalDate.parse(searchTransactionDTO.getDataFinal(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));

            return findByDateName(dataInicio, dataFim, searchTransactionDTO.getNomeOperador());
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
        BigDecimal saldoTotal = transactionHelper.calculateSaldoTotal();
        BigDecimal saldoPeriodo = transactionHelper.calculateSaldoPeriodo(listTransaction);

        if (listTransaction.isEmpty()){
            throw new TransactionException("Sem transações disponíveis");
        }

        return TransactionsDTO
                .builder()
                .transactions(listTransaction)
                .saldoPeriodo(saldoPeriodo)
                .saldoTotal(saldoTotal)
                .build();
    }

    
}
