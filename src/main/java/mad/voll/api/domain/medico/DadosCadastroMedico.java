package mad.voll.api.domain.medico;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import mad.voll.api.domain.endereco.DadosEndereco;

public record DadosCadastroMedico(
		@NotBlank String nome,
		@NotBlank String email,
		@NotBlank String telefone,
		@NotBlank @Pattern(regexp = "\\d{4,6}") String crm,
		@NotNull Especialidade especialidade,
		@NotNull @Valid DadosEndereco endereco,
		Boolean excluido) {

}
