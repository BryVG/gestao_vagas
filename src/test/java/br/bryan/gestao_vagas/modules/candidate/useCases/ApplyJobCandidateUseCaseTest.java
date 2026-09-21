package br.bryan.gestao_vagas.modules.candidate.useCases;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.assertj.core.api.Assertions.assertThat;

import br.bryan.gestao_vagas.exceptions.UserNotFoundException;
import br.bryan.gestao_vagas.modules.candidate.CandidateRepository;
import br.bryan.gestao_vagas.modules.company.repositories.JobRepository;
import br.bryan.gestao_vagas.modules.candidate.UseCases.ApplyJobCandidateUseCase;

@ExtendWith (MockitoExtension.class)
public class ApplyJobCandidateUseCaseTest {
    @InjectMocks 
    private ApplyJobCandidateUseCase applyJobCandidateUseCase;

    @Mock 
    private CandidateRepository candidateRepository;

    @Mock 
    private JobRepository jobRepository;

    @Test
    @DisplayName ("Should not be able to apply job with with candidate not found")
    public void Should_not_be_able_to_apply_job_with_with_candidate_no_found() {
        try{
            
        this.applyJobCandidateUseCase.execute(null, null);
   
    } catch(Exception e) {
           
        assertThat(e).isInstanceOf(UserNotFoundException.class);
  
    }
}
}
