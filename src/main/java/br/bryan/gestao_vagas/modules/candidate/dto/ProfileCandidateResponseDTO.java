package br.bryan.gestao_vagas.modules.candidate.dto;

import java.util.UUID;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProfileCandidateResponseDTO {

    @Schema(example = "Desenvolvedor Java Spring")
    private String description;

    @Schema(example = "Bry")
    private String username;

    @Schema(example = "bryan@gmail.com")
    private String email;

    @Schema(example = "Bry Vinicius Gomes")
    private String name;

    private UUID id;
}
