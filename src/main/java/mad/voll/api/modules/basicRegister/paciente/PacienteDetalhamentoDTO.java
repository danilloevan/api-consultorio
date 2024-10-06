package mad.voll.api.modules.basicRegister.paciente;

import mad.voll.api.modules.basicRegister.endereco.Endereco;

public record PacienteDetalhamentoDTO(Long id, String nome, String cpf, String email, String telefone,
									  Endereco Endereco) {

	public PacienteDetalhamentoDTO(Paciente paciente) {
		this(paciente.getId(), paciente.getNome(), paciente.getCpf(), paciente.getEmail(), paciente.getTelefone(),
				paciente.getEndereco());
	}
}
