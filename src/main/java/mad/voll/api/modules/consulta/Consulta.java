package mad.voll.api.modules.consulta;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import mad.voll.api.modules.basicRegister.medico.Medico;
import mad.voll.api.modules.basicRegister.paciente.Paciente;

@Entity(name = "consultas")
@Table(name = "consultas")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Builder
public class Consulta {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_medico")
	private Medico medico;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_paciente")
	private Paciente paciente;
	
	@Column(name = "data_hora")
	private LocalDateTime dataHora;
	
	@Column(name = "cancelada")
	private Boolean cancelada;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "motivo_cancelamento")
	private MotivoCancelamento motivoCancelamento;

	public Consulta(Medico medico, Paciente paciente, LocalDateTime dataHora, Boolean cancelada,
			MotivoCancelamento motivoCancelamento) {
		this.medico = medico;
		this.paciente = paciente;
		this.dataHora = dataHora;
		this.cancelada = cancelada;
		this.motivoCancelamento = motivoCancelamento;
	}
}