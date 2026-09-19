package com.metaindustria.api.controller;

import com.metaindustria.api.dto.FuncionarioRequest;
import com.metaindustria.api.model.Funcionario;
import com.metaindustria.api.service.FuncionarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/funcionarios")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class FuncionarioController {

    private final FuncionarioService funcionarioService;

    // POST /funcionarios — Cadastrar funcionario
    @PostMapping
    public ResponseEntity<Funcionario> criar(@RequestBody FuncionarioRequest dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(funcionarioService.criar(dto));
    }

    // GET /funcionarios — Listar todos
    @GetMapping
    public ResponseEntity<List<Funcionario>> listar() {
        return ResponseEntity.ok(funcionarioService.listarTodos());
    }

    // GET /funcionarios/{id} — Buscar por ID
    @GetMapping("/{id}")
    public ResponseEntity<Funcionario> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(funcionarioService.buscarPorId(id));
    }

    // PUT /funcionarios/{id} — Atualizar
    @PutMapping("/{id}")
    public ResponseEntity<Funcionario> atualizar(@PathVariable Long id, @RequestBody FuncionarioRequest dto) {
        return ResponseEntity.ok(funcionarioService.atualizar(id, dto));
    }

    // DELETE /funcionarios/{id} — Remover
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        funcionarioService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
