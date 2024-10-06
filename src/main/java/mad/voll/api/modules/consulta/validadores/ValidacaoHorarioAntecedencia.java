package mad.voll.api.modules.consulta.validadores;

import java.time.Duration;
import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

import mad.voll.api.modules.consulta.DadosAgendamentoConsulta;

@Component
public class ValidacaoHorarioAntecedencia implements ValidadorAgendamento {

	public void validar(DadosAgendamentoConsulta dadosConsulta) {
		LocalDateTime dataConsulta = dadosConsulta.dataHora();
		
		if(Duration.between(LocalDateTime.now(), dataConsulta).toMinutes() < 30) {
			throw new RuntimeException("A consulta só poderá ser agendata com pelo menos 30 minutos de antecedência.");
		}
	}
}
