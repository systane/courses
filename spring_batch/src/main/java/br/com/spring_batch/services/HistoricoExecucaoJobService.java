package br.com.spring_batch.services;

import br.com.spring_batch.entities.HistoricoExecucaoJob;
import br.com.spring_batch.repositories.HistoricoExecucaoJobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HistoricoExecucaoJobService {

    private final HistoricoExecucaoJobRepository historicoExecucaoJobRepository;

    @Autowired
    public HistoricoExecucaoJobService(HistoricoExecucaoJobRepository historicoExecucaoJobRepository) {
        this.historicoExecucaoJobRepository = historicoExecucaoJobRepository;
    }

    private HistoricoExecucaoJob save(HistoricoExecucaoJob historicoExecucaoJob){
        return historicoExecucaoJobRepository.save(historicoExecucaoJob);
    }

    private HistoricoExecucaoJob findById(Long id){
        return historicoExecucaoJobRepository.findById(id).orElse(null);
    }
}
