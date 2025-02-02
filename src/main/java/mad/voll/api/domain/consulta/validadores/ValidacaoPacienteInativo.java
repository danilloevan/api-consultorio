package mad.voll.api.domain.consulta.validadores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mad.voll.api.domain.consulta.DadosAgendamentoConsulta;
import mad.voll.api.domain.paciente.Paciente;
import mad.voll.api.domain.paciente.PacienteRepository;

@Component
public class ValidacaoPacienteInativo  implements ValidadorAgendamento{

	@Autowired
	private PacienteRepository pacienteRepository;
	
	public void validar(DadosAgendamentoConsulta dadosConsulta) {
		Paciente paciente = pacienteRepository.findById(dadosConsulta.idPaciente())
				.orElseThrow(() -> new RuntimeException("Não foi possível agendar. Paciente não encontrado."));
		
		if(paciente.getExcluido() == true) {
			throw new RuntimeException("Não foi possível agendar. Paciente inativo/excluído.");
		}
	}
}
