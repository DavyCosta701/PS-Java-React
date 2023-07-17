package br.com.banco.controller;

import br.com.banco.DTO.SearchTransactionDTO;
import br.com.banco.DTO.TransactionsDTO;
import br.com.banco.service.transactionService.TransactionServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor

public class TransactionController {

    private final TransactionServiceImpl transactionService;

    @GetMapping("/")
    @ResponseStatus(HttpStatus.OK)
    @CrossOrigin(origins = "http://localhost:3000")
    public TransactionsDTO buscaTransacao(@RequestParam(value = "data_inicio",required = false) String dataInicio,
                                          @RequestParam(value = "data_fim",required = false) String dataFim,
                                          @RequestParam(value = "operador",required = false) String operador){
        SearchTransactionDTO searchTransactionDTO = new SearchTransactionDTO(dataInicio, dataFim, operador);
        return transactionService.searchFilter(searchTransactionDTO);
    }

}
