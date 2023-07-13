package br.com.banco.domain.repository;

import br.com.banco.domain.entity.ContaEntity;
import br.com.banco.domain.entity.TransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface TransactionRepository extends JpaRepository<TransactionEntity, Integer> {

    List<TransactionEntity> findByOperadorLike(String operador);

    List<TransactionEntity> findByDataTransferenciaBetween(LocalDate dataTransferenciaStart, LocalDate dataTransferenciaEnd);

    List<TransactionEntity> findByDataTransferenciaBetweenAndOperadorLike(LocalDate dataTransferenciaStart, LocalDate dataTransferenciaEnd, String operador);




}
