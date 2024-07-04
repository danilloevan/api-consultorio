package mad.voll.api.domain.medico;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

import mad.voll.api.domain.consulta.Consulta;
import mad.voll.api.domain.consulta.MotivoCancelamento;
import mad.voll.api.domain.endereco.DadosEndereco;
import mad.voll.api.domain.paciente.DadosCadastroPaciente;
import mad.voll.api.domain.paciente.Paciente;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@ActiveProfiles("test")
class MedicoRepositoryTest {
	
	@Autowired
	private MedicoRepository medicoRepository;
	
	@Autowired
	private TestEntityManager entityManager;

	@Test
	@DisplayName("Deve retornar null quando o médico cadastrado não está disponivel na data")
	void testEscolherMedicoAleatorioCenario1() {
		LocalDateTime proximaSegunda = LocalDate.now()
				.with(TemporalAdjusters.next(DayOfWeek.MONDAY))
				.atTime(10, 0);
		Medico medico = cadastraMedico("Doctor Who", "who@thedoctor.com", "6479", Especialidade.CARDIOLOGIA, false);
		Paciente paciente = cadastraPaciente("Antônio Chesco", "chiquim1988@hotmail.com.br", "12357548932", false);
		cadastraConsulta(medico, paciente, proximaSegunda, false, null);
		
		Medico medicoLivre = medicoRepository.escolherMedicoAleatorio(Especialidade.CARDIOLOGIA, proximaSegunda);
		
		assertThat(medicoLivre).isNull();
	}
	
	@Test
	@DisplayName("Deve retornar o médico cadastrado quando estiver com horário disponivel na data")
	void testEscolherMedicoAleatorioCenario2() {
		LocalDateTime proximaSegunda = LocalDate.now()
				.with(TemporalAdjusters.next(DayOfWeek.MONDAY))
				.atTime(10, 0);
		
		LocalDateTime proximaSegundaOutroHorario = LocalDate.now()
				.with(TemporalAdjusters.next(DayOfWeek.MONDAY))
				.atTime(8, 0);
		
		Medico medico = cadastraMedico("Doctor Who", "who@thedoctor.com", "6479", Especialidade.CARDIOLOGIA, false);
		Paciente paciente = cadastraPaciente("Antônio Chesco", "chiquim1988@hotmail.com.br", "12357548932", false);
		cadastraConsulta(medico, paciente, proximaSegunda, false, null);
		
		Medico medicoLivre = medicoRepository.escolherMedicoAleatorio(Especialidade.CARDIOLOGIA, proximaSegundaOutroHorario);
		
		assertThat(medicoLivre).isNotNull();
	}
	
	private Medico cadastraMedico(String nome, String email, String crm, Especialidade especialidade, Boolean excluido) {
		return entityManager.persist(new Medico(dadosMedico(nome, email, crm, especialidade, excluido)));
	}
	
	private Paciente cadastraPaciente(String nome, String email, String cpf, Boolean excluido) {
		return entityManager.persist(new Paciente(dadosPaciente(nome, email, cpf, excluido)));
	}
	
	private Consulta cadastraConsulta(Medico medico, Paciente paciente, LocalDateTime dataHora, Boolean cancelada, 
			MotivoCancelamento motivoCancelamento) {
		return entityManager.persist(new Consulta(medico, paciente, dataHora, cancelada, motivoCancelamento));
	}
	
	private DadosCadastroMedico dadosMedico(String nome, String email, String crm, Especialidade especialidade, Boolean excluido) {
		return new DadosCadastroMedico(nome,
				email,
				"85999999999",
				crm,
				especialidade,
				dadosEndereco(),
				excluido);
	}
	
	private DadosCadastroPaciente dadosPaciente(String nome, String email, String cpf, Boolean excluido) {
		return new DadosCadastroPaciente(nome,
				email,
				"85999999999",
				cpf,
				dadosEndereco(),
				excluido);
	}
		
	private DadosEndereco dadosEndereco() {
		return new DadosEndereco("Av. A",
				"Nova Metrópole", 
				"Caucaia", 
				"CE", 
				"61600000", 
				"1024", 
				"Bloco B");
	}

}
