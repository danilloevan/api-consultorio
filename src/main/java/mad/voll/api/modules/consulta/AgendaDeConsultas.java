package mad.voll.api.modules.consulta;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mad.voll.api.modules.consulta.validadores.ValidadorCancelamento;
import mad.voll.api.modules.consulta.validadores.ValidadorAgendamento;
import mad.voll.api.modules.basicRegister.medico.Medico;
import mad.voll.api.modules.basicRegister.medico.MedicoRepository;
import mad.voll.api.modules.basicRegister.paciente.Paciente;
import mad.voll.api.modules.basicRegister.paciente.PacienteRepository;

@Service
public class AgendaDeConsultas {
	
	@Autowired private ConsultaRepository consultaRepository;
	@Autowired private MedicoRepository medicoRepository;
	@Autowired private PacienteRepository pacienteRepository;
	@Autowired private List<ValidadorAgendamento> validadoresAgendamento;
	@Autowired private ValidadorCancelamento validadorCancelamento;

	public DadosDetalhamentoConsulta agendar(DadosAgendamentoConsulta dadosAgendamento) {
		Paciente paciente = pacienteRepository.findById(dadosAgendamento.idPaciente()).orElseThrow(() -> new NullPointerException("Paciente não encontrado."));
		Medico medico = escolherMedico(dadosAgendamento);
		
		validadoresAgendamento.forEach(validador -> validador.validar(dadosAgendamento));
		
		Consulta consulta = Consulta.builder()
			.medico(medico)
			.paciente(paciente)
			.dataHora(dadosAgendamento.dataHora())
			.cancelada(false)
			.build();
		
		consultaRepository.save(consulta);
		
		return new DadosDetalhamentoConsulta(consulta);
	}
	
	public Medico escolherMedico(DadosAgendamentoConsulta dadosAgendamento) {
		if(dadosAgendamento.idMedico() != null){
			return medicoRepository.getReferenceById(dadosAgendamento.idMedico());
		}
		
		if (dadosAgendamento.especialidade() == null) {
			throw new NullPointerException("Especialidade não selecionada.");
		}
		
		return medicoRepository.escolherMedicoAleatorio(dadosAgendamento.especialidade(), dadosAgendamento.dataHora());
	}
	
	public DadosDetalhamentoConsulta cancelar(DadosCancelamentoConsulta dadosCancelamento) {
		Consulta consulta = consultaRepository.findById(dadosCancelamento.idConsulta())
				.orElseThrow(() -> new RuntimeException("Não foi possível cancelar. Consulta não encontrada."));
		
		validadorCancelamento.validar(consulta);
		
		consulta.setCancelada(true);
		consulta.setMotivoCancelamento(dadosCancelamento.motivo());
		
		return new DadosDetalhamentoConsulta(consulta);
	}
}
