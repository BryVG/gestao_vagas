package br.bryan.gestao_vagas.modules.candidate;

import lombok.Data;

import java.util.UUID;
import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.validator.constraints.Length;

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
    private String name;

    @NotBlank
    @Pattern(regexp = "^(?!\\s*$).+", message = "O campo username não deve conter espaços")
    private String username;

    @Email(message = "O campo (email) deve conter um e-mail válido")
    private String email;

    @Length(min = 10, max = 20)
    private String password;
    private String description;
    private String Curriculum;

    @CreationTimestamp
    private LocalDateTime createdAt;
}
