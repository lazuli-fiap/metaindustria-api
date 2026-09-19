package com.metaindustria.api.dto;

import com.metaindustria.api.model.Certificado;

import java.time.LocalDate;

// Formato de saida "achatado": evita expor a entidade Funcionario inteira
// (e o ciclo Funcionario <-> Certificado) no JSON.
public class CertificadoResponse {
    public Long id;
    public String maquina;
    public String descricao;
    public LocalDate dataEmissao;
    public LocalDate dataValidade;
    public boolean valido;
    public Long funcionarioId;
    public String funcionarioNome;

    public static CertificadoResponse de(Certificado c) {
        CertificadoResponse r = new CertificadoResponse();
        r.id = c.getId();
        r.maquina = c.getMaquina();
        r.descricao = c.getDescricao();
        r.dataEmissao = c.getDataEmissao();
        r.dataValidade = c.getDataValidade();
        r.valido = c.isValido();
        r.funcionarioId = c.getFuncionario().getId();
        r.funcionarioNome = c.getFuncionario().getNome();
        return r;
    }
}
