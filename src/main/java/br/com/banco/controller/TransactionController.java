package br.com.banco.controller;

import br.com.banco.DTO.SearchTransactionDTO;
import br.com.banco.DTO.TransactionsDTO;
import br.com.banco.service.TransactionServiceImpl;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor

public class TransactionController {

    private final TransactionServiceImpl transactionService;

    @PostMapping("/")
    @ResponseStatus(HttpStatus.OK)
    public TransactionsDTO buscaTransacao(@RequestBody SearchTransactionDTO searchTransactionDTO){
        return transactionService.searchFilter(searchTransactionDTO);
    }

}
