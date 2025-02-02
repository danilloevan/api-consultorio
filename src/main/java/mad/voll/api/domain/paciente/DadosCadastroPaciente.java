package mad.voll.api.domain.paciente;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import mad.voll.api.domain.endereco.DadosEndereco;

public record DadosCadastroPaciente(
		@NotBlank String nome,

		@NotBlank String email,

		@NotBlank String telefone,

		@NotBlank String cpf,

		@NotNull @Valid DadosEndereco endereco,
		
		@NotNull @Valid Boolean excluido) {

}
