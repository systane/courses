package br.com.spring_batch.entities;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "historicoexecucaoitemjob")
public class HistoricoExecucaoItemJob {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "historicoexecucaoitemjob_historico_execucao_item_id_seq")
    private Long historico_execucao_item_id;

    @Column(name = "fluxo_id")
    private Long fluxo_id;

    @Column(name = "trilha_id")
    private Long trilha_id;

    @Column(name = "erroexecucao")
    private String erroExecucao;

    @Column(name = "cod_historico_execucao_id")
    private Long codHistoricoExecucaoJob;
}
