package mad.voll.api.domain.consulta.validadores;

import java.time.Duration;
import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

import mad.voll.api.domain.consulta.Consulta;

@Component
public class ValidadorCancelamento {
	
	public void validar(Consulta consulta) {
		if (Duration.between(LocalDateTime.now(), consulta.getDataHora()).toHours() < 24) {
			throw new RuntimeException("O cancelamento deve ser até 24 hora antes do agendamento.");
		}
	}

}
