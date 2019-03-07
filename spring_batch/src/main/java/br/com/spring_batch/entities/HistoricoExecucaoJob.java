package br.com.spring_batch.entities;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
@Data
public class HistoricoExecucaoJob {

    @Id
    private Long historico_execucao_id;
    private String erroExecucao;
    private LocalDateTime dataInicioExecucao;
    private LocalDateTime dataFimExecucao;

}
