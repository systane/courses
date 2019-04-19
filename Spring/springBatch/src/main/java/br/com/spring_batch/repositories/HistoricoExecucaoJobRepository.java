package br.com.spring_batch.repositories;

import br.com.spring_batch.entities.HistoricoExecucaoJob;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HistoricoExecucaoJobRepository extends JpaRepository<HistoricoExecucaoJob, Long> {
}
