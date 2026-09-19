package com.metaindustria.api.controller;

import com.metaindustria.api.dto.CameraRequest;
import com.metaindustria.api.model.Camera;
import com.metaindustria.api.service.CameraService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cameras")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class CameraController {

    private final CameraService cameraService;

    // POST /cameras — Cadastrar camera
    @PostMapping
    public ResponseEntity<Camera> criar(@RequestBody CameraRequest dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(cameraService.criar(dto));
    }

    // GET /cameras — Listar todas
    @GetMapping
    public ResponseEntity<List<Camera>> listar() {
        return ResponseEntity.ok(cameraService.listarTodas());
    }

    // GET /cameras/{id} — Buscar por ID
    @GetMapping("/{id}")
    public ResponseEntity<Camera> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(cameraService.buscarPorId(id));
    }

    // PUT /cameras/{id} — Atualizar
    @PutMapping("/{id}")
    public ResponseEntity<Camera> atualizar(@PathVariable Long id, @RequestBody CameraRequest dto) {
        return ResponseEntity.ok(cameraService.atualizar(id, dto));
    }

    // DELETE /cameras/{id} — Remover
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        cameraService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
