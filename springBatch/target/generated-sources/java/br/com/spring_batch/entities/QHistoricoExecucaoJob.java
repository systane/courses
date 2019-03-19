package br.com.spring_batch.entities;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QHistoricoExecucaoJob is a Querydsl query type for HistoricoExecucaoJob
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QHistoricoExecucaoJob extends EntityPathBase<HistoricoExecucaoJob> {

    private static final long serialVersionUID = -1305093550L;

    public static final QHistoricoExecucaoJob historicoExecucaoJob = new QHistoricoExecucaoJob("historicoExecucaoJob");

    public final DateTimePath<java.time.LocalDateTime> dataFimExecucao = createDateTime("dataFimExecucao", java.time.LocalDateTime.class);

    public final DateTimePath<java.time.LocalDateTime> dataInicioExecucao = createDateTime("dataInicioExecucao", java.time.LocalDateTime.class);

    public final StringPath erroExecucao = createString("erroExecucao");

    public final NumberPath<Long> historico_execucao_id = createNumber("historico_execucao_id", Long.class);

    public QHistoricoExecucaoJob(String variable) {
        super(HistoricoExecucaoJob.class, forVariable(variable));
    }

    public QHistoricoExecucaoJob(Path<? extends HistoricoExecucaoJob> path) {
        super(path.getType(), path.getMetadata());
    }

    public QHistoricoExecucaoJob(PathMetadata metadata) {
        super(HistoricoExecucaoJob.class, metadata);
    }

}

