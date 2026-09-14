package br.bryan.gestao_vagas.modules.candidate.UseCases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.bryan.gestao_vagas.exceptions.UserFoundException;
import br.bryan.gestao_vagas.modules.candidate.CandidateEntity;
import br.bryan.gestao_vagas.modules.candidate.CandidateRepository;
import org.springframework.security.crypto.password.PasswordEncoder;

@Service
public class CreateCandidateUseCase {

    @Autowired
    private CandidateRepository candidateRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;
    
    public CandidateEntity execute(CandidateEntity candidateEntity) {
        this.candidateRepository.findByUsernameOrEmail(candidateEntity.getUsername(),candidateEntity.getEmail())
        .ifPresent((user)-> {
            throw new UserFoundException();
        });
        var password = this.passwordEncoder.encode(candidateEntity.getPassword());
        candidateEntity.setPassword(password);
        return this.candidateRepository.save(candidateEntity);
    }
}
