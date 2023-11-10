package br.com.banco.service.transactionService;

import br.com.banco.DTO.NewTransactionDTO;
import br.com.banco.DTO.SearchTransactionDTO;
import br.com.banco.DTO.TransactionsDTO;

import java.time.LocalDate;

public interface TransactionService {

    public TransactionsDTO searchFilter(SearchTransactionDTO searchTransactionDTO);
    public TransactionsDTO findByDate(LocalDate dataInicial, LocalDate dataFinal);
    public TransactionsDTO findByName(String nome);
    public TransactionsDTO findByDateName(LocalDate dataInicial, LocalDate dataFinal, String operador);
    public TransactionsDTO findAll();
    public void makeTransaction(NewTransactionDTO newTransactionDTO);

}
