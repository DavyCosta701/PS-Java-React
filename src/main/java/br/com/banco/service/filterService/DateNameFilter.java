package br.com.banco.service.filterService;

import br.com.banco.DTO.SearchTransactionDTO;
import br.com.banco.DTO.TransactionsDTO;
import br.com.banco.exception.DateException;
import br.com.banco.service.transactionService.TransactionServiceImpl;
import lombok.AllArgsConstructor;
import lombok.Builder;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
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
            try {
                LocalDate dataInicio = LocalDate.parse(searchTransactionDTO.getDataInicial().trim(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                LocalDate dataFim = LocalDate.parse(searchTransactionDTO.getDataFinal().trim(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));

                return transactionService.findByDateName(dataInicio, dataFim, searchTransactionDTO.getNomeOperador());
            }catch (DateTimeParseException exception){
                throw new DateException("Formato de data inválido");
            }
        }
        return this.nextFilter != null ? this.nextFilter.filterSearch(searchTransactionDTO) : transactionService.findAll();
    }
}
