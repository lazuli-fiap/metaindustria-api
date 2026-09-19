package com.metaindustria.api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "funcionarios")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Funcionario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false, unique = true)
    private String matricula;

    @Column(nullable = false)
    private String cargo;

    @Column(nullable = false)
    private String setor;

    // Ignorado no JSON pra nao vir junto do GET /funcionarios (lista pode
    // ficar pesada) e pra nao criar ciclo com Certificado.funcionario. Quem
    // quiser os certificados de um funcionario usa GET /certificados/funcionario/{id}.
    @JsonIgnore
    @OneToMany(mappedBy = "funcionario", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Certificado> certificados = new ArrayList<>();
}
