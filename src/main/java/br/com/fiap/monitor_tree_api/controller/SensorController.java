package br.com.fiap.monitor_tree_api.controller;

import br.com.fiap.monitor_tree_api.model.Sensor;
import br.com.fiap.monitor_tree_api.repository.SensorRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sensores")
@RequiredArgsConstructor
public class SensorController {

    private final SensorRepository sensorRepository;


    @PostMapping
    public Sensor cadastrar(@RequestBody @Valid Sensor sensor) {
        return sensorRepository.save(sensor);
    }

    @GetMapping
    public List<Sensor> listar() {
        return sensorRepository.findAll();
    }


    @GetMapping("/{id}")
    public Sensor buscarPorId(@PathVariable Long id) {
        return sensorRepository.findById(id).orElseThrow();
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        sensorRepository.deleteById(id);
    }
}