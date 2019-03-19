package br.com.spring_batch.entities;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Entity
@Data
@Table(name = "historicoexecucaojob")
public class HistoricoExecucaoJob {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "historicoexecucaojob_historico_execucao_id_seq")
    private Long historico_execucao_id;

    @Column(name = "datainicioexecucao")
    @NotNull
    private LocalDateTime dataInicioExecucao;

    @Column(name = "datafimexecucao")
    private LocalDateTime dataFimExecucao;

    @Column(name = "quantidadeparaprocessar")
    private Integer quantidadeParaProcessar;

    @Column(name = "quantidadeprocessada")
    private Integer quantidadeProcessada;

    @Column(name = "erroexecucao")
    private String erroExecucao;

    @OneToMany(cascade = CascadeType.PERSIST)
    private List<HistoricoExecucaoItemJob> historicoExecucaoItemJobList;


    public boolean isProcessamentoCompleto(){
        if(Objects.isNull(quantidadeParaProcessar)){
            return false;
        }
        return quantidadeProcessada.equals(quantidadeParaProcessar);
    }
}
