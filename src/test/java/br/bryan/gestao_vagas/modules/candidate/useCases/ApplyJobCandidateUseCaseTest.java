package br.bryan.gestao_vagas.modules.candidate.useCases;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.exceptions.misusing.PotentialStubbingProblem;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;
import java.util.UUID;

import br.bryan.gestao_vagas.exceptions.JobNotFoundException;
import br.bryan.gestao_vagas.exceptions.UserNotFoundException;
import br.bryan.gestao_vagas.modules.candidate.CandidateEntity;
import br.bryan.gestao_vagas.modules.candidate.CandidateRepository;
import br.bryan.gestao_vagas.modules.company.entities.JobEntity;
import br.bryan.gestao_vagas.modules.company.repositories.JobRepository;
import br.bryan.gestao_vagas.modules.candidate.UseCases.ApplyJobCandidateUseCase;
import br.bryan.gestao_vagas.modules.candidate.entity.ApplyEntity;
import br.bryan.gestao_vagas.modules.candidate.repository.ApplyJobRepository;

@ExtendWith (MockitoExtension.class)
public class ApplyJobCandidateUseCaseTest {
    @InjectMocks 
    private ApplyJobCandidateUseCase applyJobCandidateUseCase;

    @Mock 
    private CandidateRepository candidateRepository;

    @Mock 
    private JobRepository jobRepository;

    @Mock
    private ApplyEntity applyEntity;

    @Mock 
    private ApplyJobRepository applyJobRepository;

    @Test
    @DisplayName ("Should not be able to apply job with with candidate not found")
    public void Should_not_be_able_to_apply_job_with_with_candidate_no_found() {
        try{
            
        this.applyJobCandidateUseCase.execute(null, null);
   
    } catch(Exception e) {
           
        assertThat(e).isInstanceOf(UserNotFoundException.class);
  
    }
}
    @Test 
    @DisplayName ("should not be able to apply job wih job not found")
    public void should_not_be_able_to_apply_job_with_job_not_found() {
        var idCandidate = UUID.randomUUID();

        var candidate = new CandidateEntity();
        
        candidate.setId(idCandidate);

        when(candidateRepository.findById(idCandidate)).thenReturn(Optional.of(candidate));

        try {
            applyJobCandidateUseCase.execute(idCandidate, null);
        }
        catch(Exception e) {
            assertThat(e).isInstanceOf(JobNotFoundException.class);
        }
    }
    @Test 
    public void should_be_able_to_create_a_new_apply_job() {
        
        var idCandidate = UUID.randomUUID();
        var idJob = UUID.randomUUID();

        var applyJob = ApplyEntity.builder().candidateId(idCandidate)
        .jobId(idJob).build();

        var applyJobCreated = ApplyEntity.builder().id(UUID.randomUUID()).build();

        when(candidateRepository.findById(idCandidate)).thenReturn(Optional.of(new CandidateEntity()));
        when(jobRepository.findById(idJob)).thenReturn(Optional.of(new JobEntity()));

        when(applyJobRepository.save(applyJob)).thenReturn(applyJobCreated);

        var result = applyJobCandidateUseCase.execute(idCandidate, idJob);

        assertThat(result).hasFieldOrProperty("id");
        assertNotNull(result.getId());
    }
}
