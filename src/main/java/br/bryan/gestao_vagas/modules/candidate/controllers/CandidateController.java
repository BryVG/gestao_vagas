package br.bryan.gestao_vagas.modules.candidate.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import br.bryan.gestao_vagas.modules.candidate.CandidateEntity;

import jakarta.validation.Valid;
import br.bryan.gestao_vagas.modules.candidate.UseCases.CreateCandidateUseCase;

@RestController
@RequestMapping("/candidates")
public class CandidateController {

    @Autowired
    
    private CreateCandidateUseCase createCandidateUseCase;

    @PostMapping("/")
    public ResponseEntity<Object> create(@Valid @RequestBody CandidateEntity candidateEntity) {
        // Lógica para criar um candidato
        try {
            var result = this.createCandidateUseCase.execute(candidateEntity);
            return ResponseEntity.ok().body(result);
        } catch(Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
}}
