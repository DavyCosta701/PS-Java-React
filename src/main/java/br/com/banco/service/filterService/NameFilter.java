package br.com.banco.service.filterService;

import br.com.banco.DTO.SearchTransactionDTO;
import br.com.banco.DTO.TransactionsDTO;
import br.com.banco.service.TransactionServiceImpl;
import lombok.AllArgsConstructor;
import lombok.Builder;

import java.util.Objects;

@AllArgsConstructor
@Builder
public class NameFilter implements FilterService{

    FilterService nextFilter;
    TransactionServiceImpl transactionService;

    @Override
    public TransactionsDTO filterSearch(SearchTransactionDTO searchTransactionDTO) {
        if (!Objects.equals(searchTransactionDTO.getNomeOperador(), "") &&
                Objects.equals(searchTransactionDTO.getDataInicial(), "") &&
                Objects.equals(searchTransactionDTO.getDataFinal(), "")) {
            return transactionService.findByName(searchTransactionDTO.getNomeOperador());
        }
        return this.nextFilter != null ? this.nextFilter.filterSearch(searchTransactionDTO) : transactionService.findAll();
    }
}
