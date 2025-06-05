package br.com.fiap.monitor_tree_api.config;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import br.com.fiap.monitor_tree_api.model.*;
import br.com.fiap.monitor_tree_api.repository.*;

import jakarta.annotation.PostConstruct;

@Configuration
public class DatabaseSeeder {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private SensorRepository sensorRepository;

    @Autowired
    private LeituraRepository leituraRepository;

    @Autowired
    private AlertaRepository alertaRepository;

    @PostConstruct
    public void init() {
        // Usuários
        var celina = Usuario.builder()
                .nome("Celina Alcantara")
                .cpf("443.600.840-13")
                .telefone("11999999999")
                .email("celina@fiap.com.br")
                .senha(passwordEncoder.encode("12345"))
                .role(UserRole.ADMIN)
                .build();

        var ana = Usuario.builder()
                .nome("Ana Carolina")
                .cpf("858.049.070-77")
                .telefone("11988888888")
                .email("ana@fiap.com.br")
                .senha(passwordEncoder.encode("1234"))
                .role(UserRole.USER)
                .build();

        usuarioRepository.saveAll(List.of(celina, ana));

        // Sensores
        var sensor1 = Sensor.builder()
                .nome("Sensor Temperatura 1")
                .tipo(TipoSensor.TEMPERATURA)
                .localizacao("Sala 1")
                .dataCriacao(LocalDateTime.now().minusDays(10))
                .build();

        var sensor2 = Sensor.builder()
                .nome("Sensor Umidade 1")
                .tipo(TipoSensor.UMIDADE)
                .localizacao("Sala 2")
                .dataCriacao(LocalDateTime.now().minusDays(5))
                .build();

        sensorRepository.saveAll(List.of(sensor1, sensor2));

        // Leituras
        var leitura1 = Leitura.builder()
                .sensor(sensor1)
                .valor(55.0)
                .unidade("CELSIUS")
                .dataHora(LocalDateTime.now().minusHours(2))
                .build();

        var leitura2 = Leitura.builder()
                .sensor(sensor2)
                .valor(80.0)
                .unidade("PORCENTAGEM")
                .dataHora(LocalDateTime.now().minusHours(1))
                .build();

        leituraRepository.saveAll(List.of(leitura1, leitura2));

        // Alertas
        var alerta1 = Alerta.builder()
                .sensor(sensor1)
                .descricao("Temperatura acima do limite")
                .valor(55.0)
                .limite(50.0)
                .tipoAlerta("TEMPERATURA_ALTA")
                .status(StatusAlerta.ATIVO)
                .dataHora(LocalDateTime.now().minusHours(2))
                .build();

        var alerta2 = Alerta.builder()
                .sensor(sensor2)
                .descricao("Umidade acima do limite")
                .valor(80.0)
                .limite(75.0)
                .tipoAlerta("UMIDADE_ALTA")
                .status(StatusAlerta.RESOLVIDO)
                .dataHora(LocalDateTime.now().minusHours(1))
                .build();

        alertaRepository.saveAll(List.of(alerta1, alerta2));
    }
}

