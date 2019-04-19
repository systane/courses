package br.com.spring_batch.services;

import br.com.spring_batch.entities.HistoricoExecucaoItemJob;
import br.com.spring_batch.repositories.HistoricoExecucaoItemJobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HistoricoExecucaoItemJobService {

    private final HistoricoExecucaoItemJobRepository historicoExecucaoItemJobRepository;

    @Autowired
    public HistoricoExecucaoItemJobService(HistoricoExecucaoItemJobRepository historicoExecucaoItemJobRepository) {
        this.historicoExecucaoItemJobRepository = historicoExecucaoItemJobRepository;
    }

    public HistoricoExecucaoItemJob findById(Long id){
        return historicoExecucaoItemJobRepository.findById(id).orElse(null);
    }
}
