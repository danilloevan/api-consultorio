package mad.voll.api.modules.consulta;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

import mad.voll.api.modules.basicRegister.medico.Medico;
import mad.voll.api.modules.basicRegister.medico.MedicoRepository;
import mad.voll.api.modules.basicRegister.paciente.Paciente;
import mad.voll.api.modules.basicRegister.paciente.PacienteRepository;

public class ValidacoesConsulta {
	
	final ConsultaRepository consultaRepository;
	final PacienteRepository pacienteRepository;
	final MedicoRepository medicoRepository;
	
	public ValidacoesConsulta(ConsultaRepository consultaRepository, PacienteRepository pacienteRepository,
			MedicoRepository medicoRepository) {
		this.consultaRepository = consultaRepository;
		this.pacienteRepository = pacienteRepository;
		this.medicoRepository = medicoRepository;
	}

	public void validacaoHorarioFuncionamento(DadosAgendamentoConsulta dadosConsulta) {
		LocalDateTime dataConsulta = dadosConsulta.dataHora();
		
		Boolean domingo = dataConsulta.getDayOfWeek().equals(DayOfWeek.SUNDAY);
		Boolean antesHorario = dataConsulta.getHour() < 7;
		Boolean depoisHorario = dataConsulta.getHour() > 18;
		
		if(domingo || antesHorario || depoisHorario) {
			throw new RuntimeException("A consulta só podem ser marcadas entre os horários de 07:00 e 18:00 e de Segunda à Sábado.");
		}
	}
	
	public void validacaoHorarioAntecedencia(DadosAgendamentoConsulta dadosConsulta) {
		LocalDateTime dataConsulta = dadosConsulta.dataHora();
		
		if(Duration.between(dataConsulta, LocalDateTime.now()).toMinutes() < 30) {
			throw new RuntimeException("A consulta só poderá ser agendata com pelo menos 30 minutos de antecedência.");
		}
	}
	
	public void validacaoPacienteInativo(DadosAgendamentoConsulta dadosConsulta) {
		Paciente paciente = pacienteRepository.findById(dadosConsulta.idPaciente())
				.orElseThrow(() -> new RuntimeException("Não foi possível agendar. Paciente não encontrado."));
		
		if(paciente.getExcluido() == true) {
			throw new RuntimeException("Não foi possível agendar. Paciente inativo/excluído.");
		}
	}
	
	public void validacaoMedicoInativo(DadosAgendamentoConsulta dadosConsulta) {
		Medico medico = medicoRepository.findById(dadosConsulta.idMedico())
				.orElseThrow(() -> new RuntimeException("Não foi possível agendar. Médico não encontrado."));
		
		if(medico.getExcluido() == true) {
			throw new RuntimeException("Não foi possível agendar. Médico inativo/excluído.");
		}
	}
	
	public void validacaoPacienteConsultaNoDia(DadosAgendamentoConsulta dadosConsulta) {
		
		Consulta consulta = consultaRepository.buscarPacienteConsultaNoDia(dadosConsulta.idPaciente(), dadosConsulta.dataHora());
		
		if(consulta != null) {
			throw new RuntimeException("Não foi possível agendar. O paciente já possui uma consulta agendade para hoje.");
		}
	}
	
	public void validacaoMedicoConsultaNoDia(DadosAgendamentoConsulta dadosConsulta) {
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
	
	public void validaCancelamento(Consulta consulta) {
		if (Duration.between(consulta.getDataHora(), LocalDateTime.now()).toHours() < 24) {
			throw new RuntimeException("O cancelamento deve ser até 24 hora antes do agendamento.");
		}
	}
}
