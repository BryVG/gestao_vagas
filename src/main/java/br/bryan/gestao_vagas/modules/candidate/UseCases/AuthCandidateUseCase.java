package br.bryan.gestao_vagas.modules.candidate.UseCases;

import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;

import javax.naming.AuthenticationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import br.bryan.gestao_vagas.modules.candidate.dto.AuthCandidateDTO;
import br.bryan.gestao_vagas.modules.candidate.dto.AuthCandidateResponseDTO;
import br.bryan.gestao_vagas.modules.candidate.CandidateRepository;

@Service 
public class AuthCandidateUseCase {
    
    @Value ("${security.token.secret.candidate}")
    private String secretKey;

    @Autowired 
    private CandidateRepository candidateRepository;

    @Autowired 
    private PasswordEncoder passwordEncoder;

    public AuthCandidateResponseDTO execute(AuthCandidateDTO authCandidateDTO) throws AuthenticationException {

        var candidate = this.candidateRepository.findByUsername(authCandidateDTO.getUsername())
        .orElseThrow(() -> new UsernameNotFoundException("Candidate not found"));

        var passwordMatches = this.passwordEncoder.matches(authCandidateDTO.getPassword(), candidate.getPassword());

        if(!passwordMatches) {
            throw new BadCredentialsException("Invalid password");
        }
    

    Algorithm algorithm = Algorithm.HMAC256(this.secretKey);
    var expiresIn = Instant.now().plus(Duration.ofHours(2));
    var token = JWT.create()
    .withIssuer("gestao-vagas")
    .withClaim("roles",Arrays.asList("CANDIDATE"))
    .withSubject(candidate.getId().toString())
    .withExpiresAt(expiresIn)
    .sign(algorithm);
    
    var authCandidateResponse = AuthCandidateResponseDTO.builder()
    .access_token(token)
    .expires_in(expiresIn.toEpochMilli()) // 2 hours in seconds
    .build();
    return authCandidateResponse;
    }
}
