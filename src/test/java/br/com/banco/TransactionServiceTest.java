package br.com.banco;

import br.com.banco.DTO.SearchTransactionDTO;
import br.com.banco.domain.repository.ContaRepository;
import br.com.banco.domain.repository.TransactionRepository;
import br.com.banco.service.TransactionServiceImpl;
import lombok.NoArgsConstructor;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@RunWith(SpringRunner.class)
@NoArgsConstructor
@SpringBootTest
public class TransactionServiceTest {
    
    @Autowired
    private  TransactionServiceImpl transactionService;

    @Test
    public void searchByDate_DeveRetornarTransacoesEmDeterminadoPeriodo(){
        LocalDate dataInicio = LocalDate.parse("2019-01-01", DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        LocalDate dataFim = LocalDate.parse("2019-08-07", DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        System.out.println(transactionService.findByDate(dataInicio, dataFim));
    }

    @Test
    public void searchByName_DeveRetornarTransacoesPorOperador(){
        System.out.println(transactionService.findByName("Beltrano"));
    }

    @Test
    public void searchAll_DeveBuscaTodos(){
        System.out.println(transactionService.findAll());
    }

    @Test
    public void searchByDateName_DeveBuscarPorPeriodoDeTempoENome(){
        LocalDate dataInicio = LocalDate.parse("2020-06-08", DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        LocalDate dataFim = LocalDate.parse("2021-04-01", DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        System.out.println(transactionService.findByDateName(dataInicio, dataFim, "Beltrano"));
    }



}
