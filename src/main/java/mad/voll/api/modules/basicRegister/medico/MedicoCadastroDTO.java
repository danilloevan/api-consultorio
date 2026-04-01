package mad.voll.api.modules.basicRegister.medico;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import mad.voll.api.modules.basicRegister.endereco.EnderecoDTO;

public record MedicoCadastroDTO(
		@NotBlank String nome,
		@NotBlank String email,
		@NotBlank String telefone,
		@NotBlank @Pattern(regexp = "\\d{4,6}") String crm,
		@NotNull MedicoEspecialidadeEnum especialidade,
		@NotNull @Valid EnderecoDTO endereco,
		Boolean excluido) {

}
