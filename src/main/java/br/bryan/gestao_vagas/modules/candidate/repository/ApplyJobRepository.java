package br.bryan.gestao_vagas.modules.candidate.repository;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import  br.bryan.gestao_vagas.modules.candidate.entity.ApplyEntity;

public interface ApplyJobRepository extends JpaRepository<ApplyEntity, UUID> {
    
}
