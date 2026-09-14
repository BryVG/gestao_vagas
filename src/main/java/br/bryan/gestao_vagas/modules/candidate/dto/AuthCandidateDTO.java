package br.bryan.gestao_vagas.modules.candidate.dto;

import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor 
public class AuthCandidateDTO {
    private String username;
    private String password;
}
