package br.com.fiap.monitor_tree_api.controller;

import br.com.fiap.monitor_tree_api.dto.LeituraDTO;
import br.com.fiap.monitor_tree_api.model.Leitura;
import br.com.fiap.monitor_tree_api.repository.LeituraRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/leituras")
@RequiredArgsConstructor
public class LeituraController {

    private final LeituraRepository leituraRepository;

  
    @PostMapping
    public LeituraDTO salvar(@RequestBody @Valid LeituraDTO dto) {
        Leitura leitura = dto.toEntity();
        leitura = leituraRepository.save(leitura);
        return LeituraDTO.fromEntity(leitura);
    }


    @GetMapping
    public Page<LeituraDTO> listar(Pageable pageable) {
        return leituraRepository.findAll(pageable).map(LeituraDTO::fromEntity);
    }
}