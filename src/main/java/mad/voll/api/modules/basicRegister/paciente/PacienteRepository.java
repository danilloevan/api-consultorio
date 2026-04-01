package mad.voll.api.modules.basicRegister.paciente;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PacienteRepository extends JpaRepository<Paciente, Long> {

	Page<Paciente> findByExcluidoFalse(Pageable paginacao);

}
