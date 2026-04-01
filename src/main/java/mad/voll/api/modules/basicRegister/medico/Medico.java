package mad.voll.api.modules.basicRegister.medico;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import mad.voll.api.modules.basicRegister.endereco.Endereco;

@Entity(name = "medicos")
@Table(name = "medicos")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Medico {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "nome")
	private String nome;
	
	@Column(name = "email")
	private String email;
	
	@Column(name = "telefone")
	private String telefone;
	
	@Column(name = "crm")
	private String crm;

	@Enumerated(EnumType.STRING)
	@Column(name = "especialidade")
	private MedicoEspecialidadeEnum especialidade;

	@Embedded
	private Endereco endereco;

	@Column(name = "excluido")
	private Boolean excluido;

	public Medico(MedicoCadastroDTO dados) {
		this.nome = dados.nome();
		this.email = dados.email();
		this.telefone = dados.telefone();
		this.crm = dados.crm();
		this.especialidade = dados.especialidade();
		this.endereco = new Endereco(dados.endereco());
		this.excluido = dados.excluido();
	}

	public void atualizarMedico(@Valid MedicoAtualizacaoDTO dados) {
		if (dados.nome() != null) {
			this.nome = dados.nome();
		}
		if (dados.telefone() != null) {
			this.telefone = dados.telefone();
		}
		if (dados.endereco() != null) {
			this.endereco.atualizarEndereco(dados.endereco());
		}
	}

	public void excluirMedico() {
		this.excluido = true;
	}
}
