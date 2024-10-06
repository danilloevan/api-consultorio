package mad.voll.api.modules.consulta.validadores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mad.voll.api.modules.consulta.DadosAgendamentoConsulta;
import mad.voll.api.modules.basicRegister.medico.Medico;
import mad.voll.api.modules.basicRegister.medico.MedicoRepository;

@Component
public class ValidacaoMedicoInativo implements ValidadorAgendamento{
	
	@Autowired
	private MedicoRepository medicoRepository;
	
	public void validar(DadosAgendamentoConsulta dadosConsulta) {
		if(dadosConsulta.idMedico() == null) return;
		
		Medico medico = medicoRepository.findById(dadosConsulta.idMedico())
				.orElseThrow(() -> new RuntimeException("Não foi possível agendar. Médico não encontrado."));
		
		if(medico.getExcluido() == true) {
			throw new RuntimeException("Não foi possível agendar. Médico inativo/excluído.");
		}
	}
}
