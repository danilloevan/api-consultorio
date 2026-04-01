package mad.voll.api.modules.basicRegister.medico;

import mad.voll.api.modules.basicRegister.endereco.Endereco;

public record MedicoDetalhamentoDTO(Long id, String nome, String email, String telefone, String crm,
									MedicoEspecialidadeEnum especialidade, Endereco endereco) {

	public MedicoDetalhamentoDTO(Medico medico) {
		this(medico.getId(), medico.getNome(), medico.getEmail(), medico.getTelefone(), medico.getCrm(),
				medico.getEspecialidade(), medico.getEndereco());
	}
}
