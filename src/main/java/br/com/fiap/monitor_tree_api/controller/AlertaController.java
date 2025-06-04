package br.com.fiap.monitor_tree_api.controller;

import br.com.fiap.monitor_tree_api.dto.AlertaDTO;
import br.com.fiap.monitor_tree_api.model.Alerta;
import br.com.fiap.monitor_tree_api.repository.AlertaRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/alertas")
@RequiredArgsConstructor
public class AlertaController {

    private final AlertaRepository alertaRepository;

    // POST /alertas - Salvar alerta usando DTO
    @PostMapping
    public AlertaDTO salvar(@RequestBody @Valid AlertaDTO dto) {
        Alerta alerta = dto.toEntity();
        alerta = alertaRepository.save(alerta);
        return AlertaDTO.fromEntity(alerta);
    }

    // GET /alertas - Listar alertas paginados
    @GetMapping
    public Page<AlertaDTO> listar(Pageable pageable) {
        return alertaRepository.findAll(pageable).map(AlertaDTO::fromEntity);
    }
}