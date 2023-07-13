package br.com.banco.domain.repository;

import br.com.banco.domain.entity.ContaEntity;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Component
public interface ContaRepository extends JpaRepository<ContaEntity, Integer> {

    Optional<ContaEntity> findByNome(String nome);

}
