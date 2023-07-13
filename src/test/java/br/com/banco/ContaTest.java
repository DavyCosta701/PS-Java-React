package br.com.banco;

import br.com.banco.domain.entity.ContaEntity;
import br.com.banco.domain.repository.ContaRepository;
import lombok.NoArgsConstructor;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@NoArgsConstructor
public class ContaTest {

    @Autowired
    private ContaRepository contaRepository;

    @Test
    public void searchByName(){
        ContaEntity conta = contaRepository.findByNome("Fulano").get();
        System.out.println(conta);
        Assertions.assertEquals(conta.getNome(), "Fulano");

    }

}
