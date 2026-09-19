package com.metaindustria.api.service;

import com.metaindustria.api.dto.FuncionarioRequest;
import com.metaindustria.api.exception.ResourceNotFoundException;
import com.metaindustria.api.model.Funcionario;
import com.metaindustria.api.repository.FuncionarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FuncionarioService {

    private final FuncionarioRepository funcionarioRepository;

    public Funcionario criar(FuncionarioRequest dto) {
        Funcionario f = new Funcionario();
        f.setNome(dto.nome);
        f.setMatricula(dto.matricula);
        f.setCargo(dto.cargo);
        f.setSetor(dto.setor);
        return funcionarioRepository.save(f);
    }

    public List<Funcionario> listarTodos() {
        return funcionarioRepository.findAll();
    }

    public Funcionario buscarPorId(Long id) {
        return funcionarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Funcionario nao encontrado com id: " + id));
    }

    public Funcionario atualizar(Long id, FuncionarioRequest dto) {
        Funcionario f = buscarPorId(id);
        f.setNome(dto.nome);
        f.setMatricula(dto.matricula);
        f.setCargo(dto.cargo);
        f.setSetor(dto.setor);
        return funcionarioRepository.save(f);
    }

    public void deletar(Long id) {
        Funcionario f = buscarPorId(id);
        funcionarioRepository.delete(f);
    }
}
