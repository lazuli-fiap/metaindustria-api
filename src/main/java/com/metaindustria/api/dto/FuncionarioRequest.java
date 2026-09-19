package com.metaindustria.api.dto;

// DTO para criar/atualizar Funcionario (sem expor a lista de certificados no body de entrada)
public class FuncionarioRequest {
    public String nome;
    public String matricula;
    public String cargo;
    public String setor;
}
