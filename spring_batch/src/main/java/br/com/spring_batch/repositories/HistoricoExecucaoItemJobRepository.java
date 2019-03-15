package br.com.spring_batch.repositories;

import br.com.spring_batch.entities.HistoricoExecucaoItemJob;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HistoricoExecucaoItemJobRepository extends JpaRepository<HistoricoExecucaoItemJob, Long> {

}
