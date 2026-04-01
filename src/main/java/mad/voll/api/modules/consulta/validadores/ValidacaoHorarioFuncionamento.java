package mad.voll.api.modules.consulta.validadores;

import java.time.DayOfWeek;
import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

import mad.voll.api.modules.consulta.DadosAgendamentoConsulta;

@Component
public class ValidacaoHorarioFuncionamento implements ValidadorAgendamento{

	public void validar(DadosAgendamentoConsulta dadosConsulta) {
		LocalDateTime dataConsulta = dadosConsulta.dataHora();
		
		Boolean domingo = dataConsulta.getDayOfWeek().equals(DayOfWeek.SUNDAY);
		Boolean antesHorario = dataConsulta.getHour() < 7;
		Boolean depoisHorario = dataConsulta.getHour() > 18;
		
		if(domingo || antesHorario || depoisHorario) {
			throw new RuntimeException("As consultas só podem ser marcadas entre os horários de 07:00 e 18:00 e de Segunda à Sábado.");
		}
	}
}
