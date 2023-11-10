package br.com.banco.domain.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Table(name = "transferencia")
@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
public class TransactionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(name = "data_transferencia")
    private LocalDate dataTransferencia;
    @Column(name = "valor",precision = 20, scale = 2)
    private BigDecimal valorTransferencia;
    @Column(name = "tipo")
    private String tipoTransferencia;
    @Column(name= "nome_operador_transacao")
    private String operador;
    @Column(name= "conta_id")
    private int conta_id;
    @ManyToOne
    @JoinColumn(name = "FK_CONTA")
    private ContaEntity conta;

    public TransactionEntity(LocalDate dataTransferencia, BigDecimal valorTransferencia, String tipoTransferencia, String operador, int conta_id, ContaEntity conta) {
        this.dataTransferencia = dataTransferencia;
        this.valorTransferencia = valorTransferencia;
        this.tipoTransferencia = tipoTransferencia;
        this.operador = operador;
        this.conta_id = conta_id;
        this.conta = conta;
    }
}
