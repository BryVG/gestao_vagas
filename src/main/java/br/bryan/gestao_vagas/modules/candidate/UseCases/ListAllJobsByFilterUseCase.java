package br.bryan.gestao_vagas.modules.candidate.UseCases;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.bryan.gestao_vagas.modules.company.repositories.JobRepository;
import br.bryan.gestao_vagas.modules.company.entities.JobEntity;

@Service
public class ListAllJobsByFilterUseCase {
    
    @Autowired 
    private JobRepository jobRepository;

    public List<JobEntity> execute(String filter){
        return this.jobRepository.findByDescriptionContainingIgnoreCase(filter);
    }
}
