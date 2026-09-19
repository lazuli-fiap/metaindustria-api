package com.metaindustria.api.service;

import com.metaindustria.api.dto.CameraRequest;
import com.metaindustria.api.exception.ResourceNotFoundException;
import com.metaindustria.api.model.Camera;
import com.metaindustria.api.repository.CameraRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CameraService {

    private final CameraRepository cameraRepository;

    public Camera criar(CameraRequest dto) {
        Camera c = new Camera();
        c.setNomeExibicao(dto.nomeExibicao);
        c.setUri(dto.uri);
        return cameraRepository.save(c);
    }

    public List<Camera> listarTodas() {
        return cameraRepository.findAll();
    }

    public Camera buscarPorId(Long id) {
        return cameraRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Camera nao encontrada com id: " + id));
    }

    public Camera atualizar(Long id, CameraRequest dto) {
        Camera c = buscarPorId(id);
        c.setNomeExibicao(dto.nomeExibicao);
        c.setUri(dto.uri);
        return cameraRepository.save(c);
    }

    public void deletar(Long id) {
        Camera c = buscarPorId(id);
        cameraRepository.delete(c);
    }
}
