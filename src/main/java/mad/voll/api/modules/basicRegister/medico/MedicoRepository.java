package mad.voll.api.modules.basicRegister.medico;

import java.time.LocalDateTime;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicoRepository extends JpaRepository<Medico, Long> {

	Page<Medico> findAllByExcluidoFalse(Pageable paginacao);

	@Query("""
			SELECT m FROM medicos m
			WHERE m.excluido = 'f'
			AND m.especialidade = :especialidade
			AND m.id NOT IN(
				SELECT c.medico.id FROM consultas c
				WHERE c.dataHora = :dataHora
			)
			ORDER BY RANDOM()
			LIMIT 1
			""")
	Medico escolherMedicoAleatorio(MedicoEspecialidadeEnum especialidade, LocalDateTime dataHora);

}
