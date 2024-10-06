package mad.voll.api.modules.consulta.validadores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mad.voll.api.modules.consulta.Consulta;
import mad.voll.api.modules.consulta.ConsultaRepository;
import mad.voll.api.modules.consulta.DadosAgendamentoConsulta;

@Component
public class ValidacaoPacienteConsultaNoDia implements ValidadorAgendamento{
	
	@Autowired
	private ConsultaRepository consultaRepository;

	public void validar(DadosAgendamentoConsulta dadosConsulta) {
		Consulta consulta = consultaRepository.buscarPacienteConsultaNoDia(dadosConsulta.idPaciente(), dadosConsulta.dataHora());
		
		if(consulta != null) {
			throw new RuntimeException("Não foi possível agendar. O paciente já possui uma consulta agendade para hoje.");
		}
	}
}
