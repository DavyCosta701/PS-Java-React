package br.com.banco.service;

import br.com.banco.DTO.SearchTransactionDTO;
import br.com.banco.DTO.TransactionsDTO;
import br.com.banco.domain.entity.TransactionEntity;
import br.com.banco.domain.repository.TransactionRepository;
import br.com.banco.exception.DateException;
import br.com.banco.exception.TransacaoException;
import br.com.banco.helper.TransactionHelper;
import br.com.banco.service.filterService.DateFilter;
import br.com.banco.service.filterService.DateNameFilter;
import br.com.banco.service.filterService.FilterService;
import br.com.banco.service.filterService.NameFilter;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Service

public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;
    private final TransactionHelper transactionHelper;
    public TransactionServiceImpl(TransactionRepository transactionRepository, TransactionHelper transactionHelper) {
        this.transactionRepository = transactionRepository;
        this.transactionHelper = transactionHelper;

    }

     private FilterService buildFilterChain(){
        FilterService dateFilter = new DateFilter(this, null);
        FilterService nameFilter = new NameFilter(dateFilter, this);
         return new DateNameFilter(nameFilter, this);
     }

    @Override
    public TransactionsDTO searchFilter(SearchTransactionDTO searchTransactionDTO) {
        FilterService filterChain = buildFilterChain();

        return filterChain.filterSearch(searchTransactionDTO);
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
