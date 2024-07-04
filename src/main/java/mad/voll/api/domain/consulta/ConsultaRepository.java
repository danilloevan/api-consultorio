package mad.voll.api.domain.consulta;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ConsultaRepository extends JpaRepository<Consulta, Long>{
	@Query("""
			SELECT c.dataHora FROM consultas c
			WHERE c.cancelada = 'f'
			AND DATE(c.dataHora) = DATE(:dataConsulta)
			""")
	List<LocalDateTime> buscarHorariosDia(LocalDateTime dataConsulta);

	@Query("""
			SELECT c FROM consultas c
			WHERE c.cancelada = 'f'
			AND DATE(c.dataHora) = DATE(:dataConsulta)
			AND c.paciente.id = :idPaciente
			""")
	Consulta buscarPacienteConsultaNoDia(Long idPaciente, LocalDateTime dataConsulta);

	@Query("""
			SELECT c FROM consultas c
			WHERE c.cancelada = 'f'
			AND DATE(c.dataHora) = DATE(:dataConsulta)
			AND c.medico.id = :idMedico
			""")
	List<Consulta> buscarMedicoConsultaNoDia(Long idMedico, LocalDateTime dataConsulta);

}
