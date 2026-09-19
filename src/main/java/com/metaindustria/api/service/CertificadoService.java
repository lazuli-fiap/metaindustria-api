package com.metaindustria.api.service;

import com.metaindustria.api.dto.CertificadoRequest;
import com.metaindustria.api.dto.CertificadoResponse;
import com.metaindustria.api.exception.ResourceNotFoundException;
import com.metaindustria.api.model.Certificado;
import com.metaindustria.api.model.Funcionario;
import com.metaindustria.api.repository.CertificadoRepository;
import com.metaindustria.api.repository.FuncionarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CertificadoService {

    private final CertificadoRepository certificadoRepository;
    private final FuncionarioRepository funcionarioRepository;

    public CertificadoResponse criar(CertificadoRequest dto) {
        Funcionario funcionario = funcionarioRepository.findById(dto.funcionarioId)
                .orElseThrow(() -> new ResourceNotFoundException("Funcionario nao encontrado com id: " + dto.funcionarioId));

        Certificado c = new Certificado();
        c.setMaquina(dto.maquina);
        c.setDescricao(dto.descricao);
        c.setDataEmissao(dto.dataEmissao);
        c.setDataValidade(dto.dataValidade);
        c.setFuncionario(funcionario);

        return CertificadoResponse.de(certificadoRepository.save(c));
    }

    public List<CertificadoResponse> listarTodos() {
        return certificadoRepository.findAll().stream().map(CertificadoResponse::de).toList();
    }

    public CertificadoResponse buscarPorId(Long id) {
        return CertificadoResponse.de(buscarEntidade(id));
    }

    public List<CertificadoResponse> listarPorFuncionario(Long funcionarioId) {
        return certificadoRepository.findByFuncionarioId(funcionarioId).stream().map(CertificadoResponse::de).toList();
    }

    public CertificadoResponse atualizar(Long id, CertificadoRequest dto) {
        Certificado c = buscarEntidade(id);
        c.setMaquina(dto.maquina);
        c.setDescricao(dto.descricao);
        c.setDataEmissao(dto.dataEmissao);
        c.setDataValidade(dto.dataValidade);
        return CertificadoResponse.de(certificadoRepository.save(c));
    }

    public void deletar(Long id) {
        Certificado c = buscarEntidade(id);
        certificadoRepository.delete(c);
    }

    private Certificado buscarEntidade(Long id) {
        return certificadoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Certificado nao encontrado com id: " + id));
    }
}
