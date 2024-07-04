package mad.voll.api.domain.consulta.validadores;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mad.voll.api.domain.consulta.Consulta;
import mad.voll.api.domain.consulta.ConsultaRepository;
import mad.voll.api.domain.consulta.DadosAgendamentoConsulta;

@Component
public class ValidacaoMedicoConsultaNoDia implements ValidadorAgendamento{
	
	@Autowired
	private ConsultaRepository consultaRepository;

	public void validar(DadosAgendamentoConsulta dadosConsulta) {
		if(dadosConsulta.idMedico() == null) return;
		
		LocalDateTime dataHoraConsulta = dadosConsulta.dataHora();
		List<Consulta> consultas = consultaRepository.buscarMedicoConsultaNoDia(dadosConsulta.idMedico(), dataHoraConsulta);
		
		if(!consultas.isEmpty()) {
			for (Consulta consulta : consultas) {
				if(Duration.between(dataHoraConsulta, consulta.getDataHora()).toMinutes() < 60) {
					throw new RuntimeException("Não foi possível agendar. Nesse horário já terá outra consulta em andamento.");
				}
			}
		}
	}
}
