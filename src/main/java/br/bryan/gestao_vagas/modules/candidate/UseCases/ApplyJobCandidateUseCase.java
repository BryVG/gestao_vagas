package br.bryan.gestao_vagas.modules.candidate.UseCases;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.bryan.gestao_vagas.modules.candidate.CandidateRepository;
import br.bryan.gestao_vagas.modules.candidate.repository.ApplyJobRepository;
import br.bryan.gestao_vagas.modules.company.repositories.JobRepository;
import br.bryan.gestao_vagas.exceptions.UserNotFoundException;
import br.bryan.gestao_vagas.exceptions.JobNotFoundException;
import br.bryan.gestao_vagas.modules.candidate.entity.ApplyEntity;

@Service 
public class ApplyJobCandidateUseCase {
    
    @Autowired 
    private CandidateRepository candidateRepository;

    @Autowired 
    private JobRepository jobRepository;

    @Autowired
    private ApplyJobRepository applyJobRepository;

    public ApplyEntity execute(UUID idCandidate, UUID idJob) {
        
        this.candidateRepository.findById(idCandidate)
        .orElseThrow(() -> {throw new UserNotFoundException();});
        
        this.jobRepository.findById(idJob)
        .orElseThrow(() -> {throw new JobNotFoundException();});
        
        var applyJob = ApplyEntity.builder()
        .candidateId(idCandidate)
        .jobId(idJob).build();

        applyJob = applyJobRepository.save(applyJob);
        return applyJob;
    }
}