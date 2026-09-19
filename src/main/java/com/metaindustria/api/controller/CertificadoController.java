package com.metaindustria.api.controller;

import com.metaindustria.api.dto.CertificadoRequest;
import com.metaindustria.api.dto.CertificadoResponse;
import com.metaindustria.api.service.CertificadoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/certificados")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class CertificadoController {

    private final CertificadoService certificadoService;

    // POST /certificados — Emitir certificado para um funcionario
    @PostMapping
    public ResponseEntity<CertificadoResponse> criar(@RequestBody CertificadoRequest dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(certificadoService.criar(dto));
    }

    // GET /certificados — Listar todos
    @GetMapping
    public ResponseEntity<List<CertificadoResponse>> listar() {
        return ResponseEntity.ok(certificadoService.listarTodos());
    }

    // GET /certificados/{id} — Buscar por ID
    @GetMapping("/{id}")
    public ResponseEntity<CertificadoResponse> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(certificadoService.buscarPorId(id));
    }

    // GET /certificados/funcionario/{funcionarioId} — Certificados de um funcionario
    @GetMapping("/funcionario/{funcionarioId}")
    public ResponseEntity<List<CertificadoResponse>> listarPorFuncionario(@PathVariable Long funcionarioId) {
        return ResponseEntity.ok(certificadoService.listarPorFuncionario(funcionarioId));
    }

    // PUT /certificados/{id} — Atualizar certificado
    @PutMapping("/{id}")
    public ResponseEntity<CertificadoResponse> atualizar(@PathVariable Long id, @RequestBody CertificadoRequest dto) {
        return ResponseEntity.ok(certificadoService.atualizar(id, dto));
    }

    // DELETE /certificados/{id} — Remover certificado
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        certificadoService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
