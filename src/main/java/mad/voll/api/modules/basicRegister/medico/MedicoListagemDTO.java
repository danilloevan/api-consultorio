package mad.voll.api.modules.basicRegister.medico;

public record MedicoListagemDTO(
		Long id, 
		String nome, 
		String email, 
		String crm, 
		MedicoEspecialidadeEnum especialidade) {

	public MedicoListagemDTO(Medico medico) {
		this(medico.getId(), medico.getNome(), medico.getEmail(), medico.getCrm(), medico.getEspecialidade());

	}
}
