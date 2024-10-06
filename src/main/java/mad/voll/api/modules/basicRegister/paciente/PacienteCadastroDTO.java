package mad.voll.api.modules.basicRegister.paciente;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import mad.voll.api.modules.basicRegister.endereco.EnderecoDTO;

public record PacienteCadastroDTO(
		@NotBlank String nome,

		@NotBlank String email,

		@NotBlank String telefone,

		@NotBlank String cpf,

		@NotNull @Valid EnderecoDTO endereco,
		
		@NotNull @Valid Boolean excluido) {

}
