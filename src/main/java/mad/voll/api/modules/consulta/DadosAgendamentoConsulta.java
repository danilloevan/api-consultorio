package mad.voll.api.modules.consulta;

import java.time.LocalDateTime;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import mad.voll.api.modules.basicRegister.medico.MedicoEspecialidadeEnum;

public record DadosAgendamentoConsulta(
		Long idMedico,
		
		@NotNull
		Long idPaciente,
		
		@NotNull
		@Future
		LocalDateTime dataHora,
		
		MedicoEspecialidadeEnum especialidade) {


}
