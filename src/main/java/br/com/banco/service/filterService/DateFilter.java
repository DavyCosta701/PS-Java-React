package br.com.banco.service.filterService;

import br.com.banco.DTO.SearchTransactionDTO;
import br.com.banco.DTO.TransactionsDTO;
import br.com.banco.service.TransactionServiceImpl;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

@NoArgsConstructor
@AllArgsConstructor
public class DateFilter implements FilterService{

    private TransactionServiceImpl transactionService;
    private  FilterService nextFilter;


    @Override
    public TransactionsDTO filterSearch(SearchTransactionDTO searchTransactionDTO) {
        if (!Objects.equals(searchTransactionDTO.getDataInicial(), "") &&
                !Objects.equals(searchTransactionDTO.getDataFinal(), "") &&
                Objects.equals(searchTransactionDTO.getNomeOperador(), "")) {
            LocalDate dataInicio = LocalDate.parse(searchTransactionDTO.getDataInicial(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            LocalDate dataFim = LocalDate.parse(searchTransactionDTO.getDataFinal(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            return transactionService.findByDate(dataInicio, dataFim);
        }
        return nextFilter !=null ? nextFilter.filterSearch(searchTransactionDTO) : transactionService.findAll();
    }
}
