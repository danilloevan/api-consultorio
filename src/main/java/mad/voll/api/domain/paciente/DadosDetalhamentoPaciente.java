package mad.voll.api.domain.paciente;

import mad.voll.api.domain.endereco.Endereco;

public record DadosDetalhamentoPaciente(Long id, String nome, String cpf, String email, String telefone,
		Endereco Endereco) {

	public DadosDetalhamentoPaciente(Paciente paciente) {
		this(paciente.getId(), paciente.getNome(), paciente.getCpf(), paciente.getEmail(), paciente.getTelefone(),
				paciente.getEndereco());
	}
}
