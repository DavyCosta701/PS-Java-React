package br.com.banco.service.filterService;

import br.com.banco.DTO.SearchTransactionDTO;
import br.com.banco.DTO.TransactionsDTO;

public interface FilterService {
    TransactionsDTO filterSearch(SearchTransactionDTO searchTransactionDTO);
}
