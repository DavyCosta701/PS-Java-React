package br.com.banco;

import br.com.banco.DTO.SearchTransactionDTO;
import br.com.banco.controller.TransactionController;
import br.com.banco.service.transactionService.TransactionServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.NoArgsConstructor;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@RunWith(SpringRunner.class)
@NoArgsConstructor
@WebMvcTest(TransactionController.class)
public class TransactionControllerTest {

    @Autowired
    private MockMvc mvc;
    @Autowired
    ObjectMapper objectMapper;
    @MockBean
    TransactionServiceImpl transactionService;

    @Test
    public void testApi_DeveRetornarTodosOsValores() throws Exception {
        SearchTransactionDTO searchTransactionDTO = SearchTransactionDTO.builder()
                .nomeOperador("")
                .dataInicial("")
                .dataFinal("")
                .build();
        mvc.perform( MockMvcRequestBuilders
                        .post("/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(searchTransactionDTO)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void testApi_DeveRetornarValoresComDataEspecificada() throws Exception {
        SearchTransactionDTO searchTransactionDTO = SearchTransactionDTO.builder()
                .nomeOperador("")
                .dataInicial("2019-01-01")
                .dataFinal("2021-01-01")
                .build();
        mvc.perform( MockMvcRequestBuilders
                        .post("/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(searchTransactionDTO)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void testApi_DeveRetornarValoresComDataENomeEspecificados() throws Exception {
        SearchTransactionDTO searchTransactionDTO = SearchTransactionDTO.builder()
                .nomeOperador("Beltrano")
                .dataInicial("2019-01-01")
                .dataFinal("2021-01-01")
                .build();
        mvc.perform( MockMvcRequestBuilders
                        .post("/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(searchTransactionDTO)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }


}
