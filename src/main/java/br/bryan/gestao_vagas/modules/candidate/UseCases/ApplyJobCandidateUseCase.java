package br.bryan.gestao_vagas.modules.candidate.UseCases;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.bryan.gestao_vagas.modules.candidate.CandidateRepository;
import br.bryan.gestao_vagas.modules.candidate.repository.ApplyJobRepository;
import br.bryan.gestao_vagas.modules.company.repositories.JobRepository;
import br.bryan.gestao_vagas.exceptions.UserNotFoundException;
import br.bryan.gestao_vagas.exceptions.JobNotFoundException;

@Service 
public class ApplyJobCandidateUseCase {
    
    @Autowired 
    private CandidateRepository candidateRepository;

    @Autowired 
    private JobRepository jobRepository;

    @Autowired
    private ApplyJobRepository applyJobRepository;
    
    public void execute(UUID idCandidate, UUID idJob) {
        
        this.candidateRepository.findById(idCandidate)
        .orElseThrow(() -> {throw new UserNotFoundException();});
        
        this.jobRepository.findById(idJob)
        .orElseThrow(() -> {throw new JobNotFoundException();});
        
    }
}