package mad.voll.api.domain.paciente;

public record DadosListagemPacientes(Long id, String nome, String cpf, String telefone, String email) {

	public DadosListagemPacientes(Paciente paciente) {
		this(paciente.getId(), paciente.getNome(), paciente.getCpf(), paciente.getTelefone(), paciente.getEmail());
	}
}
