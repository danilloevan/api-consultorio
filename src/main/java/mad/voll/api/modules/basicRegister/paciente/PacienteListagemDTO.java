package mad.voll.api.modules.basicRegister.paciente;

public record PacienteListagemDTO(Long id, String nome, String cpf, String telefone, String email) {

	public PacienteListagemDTO(Paciente paciente) {
		this(paciente.getId(), paciente.getNome(), paciente.getCpf(), paciente.getTelefone(), paciente.getEmail());
	}
}
