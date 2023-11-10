package br.com.banco.controller;

import br.com.banco.DTO.NewTransactionDTO;
import br.com.banco.DTO.SearchTransactionDTO;
import br.com.banco.DTO.TransactionsDTO;
import br.com.banco.service.transactionService.TransactionServiceImpl;
import lombok.AllArgsConstructor;
import netscape.javascript.JSObject;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor

public class TransactionController {

    private final TransactionServiceImpl transactionService;

    @PostMapping("/")
    @ResponseStatus(HttpStatus.OK)
    @CrossOrigin(origins = "http://localhost:3000")
    public TransactionsDTO buscaTransacao(@RequestBody SearchTransactionDTO searchTransactionDTO) {
        return transactionService.searchFilter(searchTransactionDTO);
    }

    @PostMapping("/transacao")
    @ResponseStatus(HttpStatus.OK)
    @CrossOrigin(origins = "http://localhost:3000")
    public String novaTransacao(@RequestBody NewTransactionDTO newTransactionDTO) {
        transactionService.makeTransaction(newTransactionDTO);
        return "Transaction OK";
    }

}
