package com.metaindustria.api.repository;

import com.metaindustria.api.model.Certificado;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CertificadoRepository extends JpaRepository<Certificado, Long> {
    List<Certificado> findByFuncionarioId(Long funcionarioId);
}
