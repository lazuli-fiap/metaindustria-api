package com.metaindustria.api.repository;

import com.metaindustria.api.model.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {
    boolean existsByMatricula(String matricula);
    boolean existsByMatriculaAndIdNot(String matricula, Long id);
}
