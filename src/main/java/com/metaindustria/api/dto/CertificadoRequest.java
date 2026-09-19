package com.metaindustria.api.dto;

import java.time.LocalDate;

// DTO para criar/atualizar Certificado
public class CertificadoRequest {
    public String maquina;
    public String descricao;
    public LocalDate dataEmissao;
    public LocalDate dataValidade;
    public Long funcionarioId;
}
