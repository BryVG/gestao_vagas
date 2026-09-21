package br.bryan.gestao_vagas.modules.candidate;

import lombok.Data;

import java.util.UUID;
import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.RequiredMode;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;


@Data
@Entity(name = "candidate")
public class CandidateEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    
    @Schema(example= "Daniel casparini", requiredMode = RequiredMode.REQUIRED, description = "Nome do candidato")
    private String name;

    @Schema(example= "daniel", requiredMode = RequiredMode.REQUIRED, description = "Username do candidato")
    @NotBlank
    @Pattern(regexp = "^(?!\\s*$).+", message = "O campo username não deve conter espaços")
    private String username;

    @Schema(example= "daniel@gmail.com", requiredMode = RequiredMode.REQUIRED, description = "E-mail do candidato")
    @Email(message = "O campo (email) deve conter um e-mail válido")
    private String email;

    @Schema(example= "1234Ving", minLength = 10, maxLength = 100, requiredMode = RequiredMode.REQUIRED, description = "Descrição breve do candidato")
    private String password;

    @Schema (example = "Desenvolvedor Java")
    private String description;

    private String curriculum;

    @CreationTimestamp
    private LocalDateTime createdAt;
}
