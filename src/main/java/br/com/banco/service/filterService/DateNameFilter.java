package br.com.banco.service.filterService;

import br.com.banco.DTO.SearchTransactionDTO;
import br.com.banco.DTO.TransactionsDTO;
import br.com.banco.service.TransactionServiceImpl;
import lombok.AllArgsConstructor;
import lombok.Builder;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

@AllArgsConstructor
@Builder
public class DateNameFilter implements FilterService {
    private  FilterService nextFilter;
    private  TransactionServiceImpl transactionService;

    @Override
    public TransactionsDTO filterSearch(SearchTransactionDTO searchTransactionDTO) {

        if (!Objects.equals(searchTransactionDTO.getNomeOperador(), "") &&
                !Objects.equals(searchTransactionDTO.getDataInicial(), "") &&
                !Objects.equals(searchTransactionDTO.getDataFinal(), "")
        ) {
            LocalDate dataInicio = LocalDate.parse(searchTransactionDTO.getDataInicial(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            LocalDate dataFim = LocalDate.parse(searchTransactionDTO.getDataFinal(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));

            return transactionService.findByDateName(dataInicio, dataFim, searchTransactionDTO.getNomeOperador());
        }
        return this.nextFilter != null ? this.nextFilter.filterSearch(searchTransactionDTO) : transactionService.findAll();
    }
}
